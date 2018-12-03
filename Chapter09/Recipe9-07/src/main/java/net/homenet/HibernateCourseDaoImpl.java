package net.homenet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@SuppressWarnings({ "WeakerAccess", "JpaQlInspection", "Duplicates" })
public class HibernateCourseDaoImpl implements CourseDao {
    private final SessionFactory sessionFactory;

    public HibernateCourseDaoImpl(SessionFactory sessionFactory) {
        //# Native configuration
        //Configuration configuration = new Configuration()
        //    .setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/course")
        //    .setProperty(AvailableSettings.USER, "postgres")
        //    .setProperty(AvailableSettings.PASS, "sqladmin")
        //    .setProperty(AvailableSettings.DIALECT, PostgreSQL95Dialect.class.getName())
        //    .setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true))
        //    .setProperty(AvailableSettings.HBM2DDL_AUTO, "update")
        //    //.addClass(Course.class);  //# XML mapping
        //    .addAnnotatedClass(Course.class);
        //this.sessionFactory = configuration.buildSessionFactory();

        this.sessionFactory = sessionFactory;
    }

    @Override
    public Course store(Course course) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.saveOrUpdate(course);
            tx.commit();
            return course;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long courseId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Course course = session.get(Course.class, courseId);
            session.delete(course);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Course findById(Long courseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, courseId);
        }
    }

    @Override
    public List<Course> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT ID, TITLE, BEGIN_DATE, END_DATE, FEE FROM COURSE", Course.class)
                .list();
        }
    }
}
