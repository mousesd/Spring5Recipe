package net.homenet;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings({ "JpaQlInspection", "Duplicates" })
@Repository("courseDao")
public class JpaCourseDaoImpl implements CourseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Course store(Course course) {
        return entityManager.merge(course);
    }

    @Override
    @Transactional
    public void delete(Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        entityManager.remove(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT ID, TITLE, BEGIN_DATE, END_DATE, FEE FROM COURSE"
            , Course.class);
        return query.getResultList();
    }
}
