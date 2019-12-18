package praxis.religi.repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import praxis.religi.model.Bookmark;

@Singleton
public class BookmarkRepository implements BookmarkRepositoryInf {

    @PersistenceContext
    private EntityManager entityManager;

    BookmarkRepository(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public String subscribe(Bookmark bookmark) {
        try {
            entityManager.persist(bookmark);
            return "sukses";
        } catch (Exception e) {
            return "gagal";
        }
    }

    @Override
    @Transactional
    public String unsubscribe(Bookmark bookmark) {
        try {
            String queryString = "DELETE FROM Bookmark bookmark SET status = bookmark dihapus WHERE bookmark.id_user = :id_user and bookmark.id_event = :id_event";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("bookmark", bookmark);
            int delete = query.executeUpdate();
            if (delete == 1) {
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
    public List<Bookmark> getAll(int page, int limit) {
        TypedQuery<Bookmark> query = entityManager.createQuery("Bookmark", Bookmark.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0).setMaxResults(limit);
        return query.getResultList();

    }

}