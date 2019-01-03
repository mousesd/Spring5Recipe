package net.homenet;

import net.homenet.configuration.BatchConfiguration;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);

        JobParameters jobParameters = new JobParametersBuilder()
            .addDate("date", new Date())
            .toJobParameters();

        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        BatchStatus status = jobExecution.getStatus();
        while (status.isRunning()) {
            System.out.println("Still running...");
            Thread.sleep(1000);
        }

        System.out.println("Exit status: " + jobExecution.getExitStatus().getExitCode());

        JobInstance jobInstance = jobExecution.getJobInstance();
        System.out.println("Job instance id: " + jobInstance.getId());
    }
}
