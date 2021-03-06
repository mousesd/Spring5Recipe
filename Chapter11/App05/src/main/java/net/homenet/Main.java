package net.homenet;

import net.homenet.configuration.BatchConfiguration;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);

        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);
        JobExecution jobExecution = jobLauncher.run(job
            , new JobParametersBuilder().addDate("date", new Date()).toJobParameters());

        BatchStatus status = jobExecution.getStatus();
        while (status.isRunning()) {
            Thread.sleep(1000);
        }

        System.out.println("Exit status: " + jobExecution.getExitStatus().getExitCode());
    }
}
