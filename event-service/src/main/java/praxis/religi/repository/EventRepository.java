package praxis.religi.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import praxis.religi.model.Event;

@Singleton
public class EventRepository implements EventRepositoryInf {

    @PersistenceContext
    private EntityManager entityManager;

    EventRepository(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public String create(Event event) {
        try {
            entityManager.persist(event);
            return "sukses";
        } catch (Exception e) {
            return "gagal";
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Event read(@NotNull int id) {
        String queryString = "SELECT event FROM Event event where id = :id";
        TypedQuery<Event> query = entityManager.createQuery(queryString, Event.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public String update(Event event){
        try {
            entityManager.merge(event);
            return "sukses";
        } catch (Exception e) {
            return "gagal";
        }
    }

    @Override
    @Transactional
    public String cancel(@NotNull int id){
        try {
            // tidak sepenuhnya melakukan delete, hanya melakukan set status ke tidak aktif
            String queryString = "UPDATE Event event SET status = tidakAktif where id = :id";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("id", id);
            int update = query.executeUpdate();
            if(update == 1){
                return "sukses";
            }else{
                return "gagal";
            }
        } catch (Exception e) {
            return "gagal";
        }
    }

    @Override
    @Transactional
    public List<Event> getAll(int page, int limit){
        TypedQuery<Event> query = entityManager.createQuery("from Event", Event.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0).setMaxResults(limit);
        return query.getResultList();
    }
}