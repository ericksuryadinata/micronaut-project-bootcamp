package praxis.religi.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

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
        String queryString = "SELECT user FROM User user WHERE user.email = :email and user.password = :password";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        
        return query.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        String queryString = "SELECT user FROM User user";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        return query.getResultList();
    }
}