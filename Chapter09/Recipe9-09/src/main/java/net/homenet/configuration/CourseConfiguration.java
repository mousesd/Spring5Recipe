package net.homenet.configuration;

import com.zaxxer.hikari.HikariDataSource;
import net.homenet.CourseDao;
import net.homenet.JpaCourseDaoImpl;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@SuppressWarnings("Duplicates")
@Configuration
public class CourseConfiguration {
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/course");
        dataSource.setUsername("postgres");
        dataSource.setPassword("sqladmin");
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform(PostgreSQL95Dialect.class.getName());
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("net.homenet");
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        return emf;
    }

    @Bean
    public CourseDao courseDao(EntityManagerFactory entityManagerFactory) {
        return new JpaCourseDaoImpl(entityManagerFactory);
    }
}
