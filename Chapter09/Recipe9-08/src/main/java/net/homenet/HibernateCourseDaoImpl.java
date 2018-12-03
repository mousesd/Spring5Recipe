package net.homenet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings({ "WeakerAccess", "JpaQlInspection", "Duplicates" })
@Repository("courseDao")
public class HibernateCourseDaoImpl implements CourseDao {
    private final SessionFactory sessionFactory;

    public HibernateCourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Course store(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(course);
        return course;
    }

    @Transactional
    @Override
    public void delete(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, courseId);
        session.delete(course);
    }

    @Transactional(readOnly = true)
    @Override
    public Course findById(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Course.class, courseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT ID, TITLE, BEGIN_DATE, END_DATE, FEE FROM COURSE", Course.class)
            .list();
    }
}
