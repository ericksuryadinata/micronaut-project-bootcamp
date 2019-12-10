package praxis.religi.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import praxis.religi.model.User;

@Singleton
public class AuthRepository implements AuthRepositoryInf {

    @PersistenceContext
    private EntityManager entityManager;

    AuthRepository(@CurrentSession EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public User login(@NotNull String email, @NotNull String password) {
        String queryString = "SELECT user FROM User user WHERE user.email = :email";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if(result.verified){
            return user;
        }else{
            user = null;
            return user;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        String queryString = "SELECT user FROM User user";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public User save(User user){
        user.setId(null);
        // set password here
        String passwordFromUser = user.getPassword();
        String hashedPassword = BCrypt.withDefaults().hashToString(12, passwordFromUser.toCharArray());
        user.setPassword(hashedPassword);
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean checkExistEmail(@NotNull String email){
        String queryString = "SELECT count(*) FROM User user WHERE user.email = :email";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        query.setParameter("email", email);
        Long count = query.getSingleResult();

        if(count != 1){
            return false;
        }
        
        return true;
    }
}