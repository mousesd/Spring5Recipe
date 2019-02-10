package net.homenet.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@ComponentScan("net.homenet.batch")
@EnableBatchProcessing
@PropertySource("classpath:batch.properties")
public class BatchConfiguration {
    private final Environment env;

    @Autowired
    public BatchConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("dataSource.url"));
        dataSource.setUsername(env.getProperty("dataSource.username"));
        dataSource.setPassword(env.getProperty("dataSource.password"));
        return dataSource;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.setIgnoreFailedDrops(true);
        populator.addScript(new ClassPathResource("org/springframework/batch/core/schema-h2.sql"));
        populator.addScript(new ClassPathResource("scripts/reset_user_registration.sql"));
        return populator;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    //# @EnableBatchProcessing 사용하면 아래 코드가 자동 설정
    //@Bean
    //public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    //    return new DataSourceTransactionManager(dataSource);
    //}
    //
    //@Bean
    //public JobRepositoryFactoryBean jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) {
    //    JobRepositoryFactoryBean jobRepository = new JobRepositoryFactoryBean();
    //    jobRepository.setDataSource(dataSource);
    //    jobRepository.setTransactionManager(transactionManager);
    //    return jobRepository;
    //}
    //
    //@Bean
    //public JobLauncher jobLauncher(JobRepository jobRepository) {
    //    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    //    jobLauncher.setJobRepository(jobRepository);
    //    return jobLauncher;
    //}
    //
    //@Bean
    //public JobRegistry jobRegistry() {
    //    return new MapJobRegistry();
    //}
    //
    //@Bean
    //public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor (JobRegistry jobRegistry) {
    //    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    //    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
    //    return jobRegistryBeanPostProcessor;
    //}
}
