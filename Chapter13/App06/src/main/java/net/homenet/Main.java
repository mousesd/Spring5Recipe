package net.homenet;

import net.homenet.configuration.FileReplicatorConfiguration;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(FileReplicatorConfiguration.class);
        FileReplicator fileReplicator = context.getBean(FileReplicator.class);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("fileReplicator", fileReplicator);

        JobDetail job = JobBuilder.newJob(FileReplicationJob.class)
            .withIdentity("fileReplicationJob")
            .storeDurably()
            .usingJobData(jobDataMap)
            .build();

        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("fileReplicationTrigger")
            .startAt(new Date(System.currentTimeMillis() + 5000))
            .forJob(job)
            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60)
                .repeatForever())
            .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
