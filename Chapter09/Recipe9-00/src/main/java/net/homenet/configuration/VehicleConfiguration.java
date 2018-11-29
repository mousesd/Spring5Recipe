package net.homenet.configuration;

import com.zaxxer.hikari.HikariDataSource;
import net.homenet.JdbcVehicleDaoImpl;
import net.homenet.VehicleDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class VehicleConfiguration {
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/vehicle");
        dataSource.setUsername("postgres");
        dataSource.setPassword("sqladmin");
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }

    @Bean
    public VehicleDao vehicleDao() {
        return new JdbcVehicleDaoImpl(dataSource());
    }
}
