package net.homenet.configuration;

import net.homenet.batch.RetryableUserRegistrationItemWriter;
import net.homenet.batch.UserValidationItemProcessor;
import net.homenet.domain.User;
import net.homenet.service.UserService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.ClassUtils;

@Configuration
public class UserJob {
    @Value("classpath:batchInputs/User.csv")
    private Resource input;

    @Bean
    public FlatFileItemReader<User> itemReader() {
        return new FlatFileItemReaderBuilder<User>()
            .name(ClassUtils.getShortName(FlatFileItemReader.class))
            .resource(input)
            .targetType(User.class)
            .delimited()
            .names(new String[]{ "firstName", "lastName", "company", "address", "city", "state", "zip", "county"
                , "url", "phoneNumber", "fax" })
            .build();
    }

    @Bean
    public ItemProcessor<User, User> itemProcessor() {
        return new UserValidationItemProcessor();
    }

    @Bean
    public ItemWriter<User> itemWriter(UserService userService, RetryTemplate retryTemplate) {
        return new RetryableUserRegistrationItemWriter(userService, retryTemplate);
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<User> itemReader
        , ItemProcessor<User, User> itemProcessor, ItemWriter<User> itemWriter) {
        return stepBuilderFactory.get("User registration CSV to DB step")
            .<User, User>chunk(5)
            .faultTolerant()
            .retryLimit(3)
            .retry(DeadlockLoserDataAccessException.class)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    }

    @Bean
    public Job insertIntoDbFromCsvJob(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("User registration import job")
            .start(step)
            .build();
    }
}
