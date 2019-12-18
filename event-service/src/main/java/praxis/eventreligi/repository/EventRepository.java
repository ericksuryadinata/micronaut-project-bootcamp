package praxis.eventreligi.repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.persistence.Query;
import java.util.List;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import praxis.eventreligi.model.Event;

@Singleton
public class EventRepository implements EventRepositoryInf {

    @PersistenceContext
    private EntityManager entityManager;

    EventRepository(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implementasi create yang telah dibuat di dalam model
    // persist untuk menyetujui adanya penambahan didalam tabel event
    // try catch fungsinya sama kayak if else
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

    // implementasi read
    // TypedQuery yang dikembalikan read <Event>
    @Override
    @Transactional(readOnly = true)
    public Event read(@NotNull int id) {
        String queryString = "SELECT event FROM Event event where id =:id";
        TypedQuery<Event> query = entityManager.createQuery(queryString, Event.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    // implementasi update
    // merge untuk menggabungkan yang sudah ada di tb
    @Override
    @Transactional
    public String update(Event event) {
        try {
            entityManager.merge(event);
            return "sukses";
        } catch (Exception e) {
            return "gagal";
        }
    }

    @Override
    @Transactional
    public String cancel(@NotNull int id) {
        try {
            String queryString = "UPDATE Event event SET status = tidakaktif where id= :id";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("id", id);
            int update = query.executeUpdate();
            if (update == 1) {
                return "sukses";
            } else {
                return "gagal";
            }
        } catch (Exception e) {
            return "gagal";
        }
    }

    @Override
    @Transactional
    public List<Event> getAll(int page, int limit) {
        TypedQuery<Event> query = entityManager.createQuery("from Event", Event.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0).setMaxResults(limit);
        return query.getResultList();
    }
}