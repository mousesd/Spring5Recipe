package net.homenet.configuration;

import net.homenet.Course;
import net.homenet.CourseDao;
import net.homenet.HibernateCourseDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
public class CourseConfiguration {
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/course");
        properties.setProperty(AvailableSettings.USER, "postgres");
        properties.setProperty(AvailableSettings.PASS, "sqladmin");
        properties.setProperty(AvailableSettings.DIALECT, PostgreSQL95Dialect.class.getName());
        properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.setAnnotatedClasses(Course.class);
        return sessionFactoryBean;
    }

    @Bean
    public CourseDao courseDao(SessionFactory sessionFactory) {
        return new HibernateCourseDaoImpl(sessionFactory);
    }
}
