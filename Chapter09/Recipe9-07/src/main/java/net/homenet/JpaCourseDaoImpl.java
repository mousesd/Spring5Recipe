package net.homenet;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings({ "JpaQlInspection", "Duplicates" })
public class JpaCourseDaoImpl implements CourseDao {
    private final EntityManagerFactory entityManagerFactory;

    public JpaCourseDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Course store(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Course persisted = entityManager.merge(course);
            tx.commit();
            return persisted;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.remove(course);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Course findById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Course.class, courseId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT ID, TITLE, BEGIN_DATE, END_DATE, FEE FROM COURSE");
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
