package net.homenet.configuration;

import net.homenet.domain.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class UseJob {
    @Value("classpath:batchInputs/User.csv")
    private Resource input;

    @Bean
    public DelimitedLineTokenizer lineTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("firstName", "lastName", "company", "address", "city", "state", "zip", "county"
            , "url", "phoneNumber", "fax");
        return tokenizer;
    }

    @Bean
    public BeanWrapperFieldSetMapper<User> fieldSetMapper() {
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);
        return fieldSetMapper;
    }

    @Bean
    public DefaultLineMapper<User> lineMapper(LineTokenizer lineTokenizer, FieldSetMapper<User> fieldSetMapper) {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public FlatFileItemReader<User> itemReader(LineMapper<User> lineMapper) {
        FlatFileItemReader<User> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper);
        itemReader.setResource(input);
        return itemReader;
    }

    @Bean
    public JdbcBatchItemWriter<User> itemWriter(DataSource dataSource) {
        JdbcBatchItemWriter<User> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO USER_REGISTRATION (FIRST_NAME, LAST_NAME, COMPANY, ADDRESS, CITY, STATE, ZIP" +
            ", COUNTY, URL, PHONE_NUMBER, FAX) VALUES (:firstName, :lastName, :company, :address, :city, :state, :zip" +
            ", :county, :url, :phoneNumber, :fax)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return itemWriter;
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<User> itemReader, ItemWriter<User> itemWriter) {
        return stepBuilderFactory.get("User registration CSV to DB Step")
            .<User, User>chunk(5)
            .reader(itemReader)
            .writer(itemWriter)
            .build();
    }

    @Bean
    public Job insertIntoDbFrmCsvJob(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("User registration import job")
            .start(step)
            .build();
    }
}
