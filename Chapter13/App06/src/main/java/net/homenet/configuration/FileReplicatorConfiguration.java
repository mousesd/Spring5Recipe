package net.homenet.configuration;

import net.homenet.*;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collections;

@SuppressWarnings("Duplicates")
@Configuration
public class FileReplicatorConfiguration {
    @Value("#{systemProperties['user.home']}/docs")
    private String srcDir;

    @Value("#{systemProperties['user.home']}/docs_backup")
    private String destDir;

    @Bean
    public FileCopier fileCopier() {
        return new FileCopierImpl();
    }

    @Bean
    public FileReplicator fileReplicator(FileCopier fileCopier) {
        FileReplicator replicator = new FileReplicatorImpl();
        replicator.setSrcDir(srcDir);
        replicator.setDestDir(destDir);
        replicator.setFileCopier(fileCopier);
        return replicator;
    }

    @Bean
    public JobDetailFactoryBean fileReplicatorJob(FileReplicator fileReplicator) {
        JobDetailFactoryBean fileReplicatorJob = new JobDetailFactoryBean();
        fileReplicatorJob.setJobClass(FileReplicationJob.class);
        fileReplicatorJob.setDurability(true);
        fileReplicatorJob.setJobDataAsMap(Collections.singletonMap("fileReplicator", fileReplicator));
        return fileReplicatorJob;
    }

    @Bean
    public SimpleTriggerFactoryBean fileReplicatorTrigger(JobDetail fileReplicatorJob) {
        SimpleTriggerFactoryBean fileReplicatorTrigger = new SimpleTriggerFactoryBean();
        fileReplicatorTrigger.setJobDetail(fileReplicatorJob);
        fileReplicatorTrigger.setStartDelay(5000);
        fileReplicatorTrigger.setRepeatInterval(60000);
        return fileReplicatorTrigger;
    }

    @Bean
    public SchedulerFactoryBean fileReplicatorScheduler(Trigger fileReplicatorTrigger) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(fileReplicatorTrigger);
        return scheduler;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @PostConstruct
    public void verifyExistDirectory() {
        File source = new File(srcDir);
        if (!source.exists()) {
            source.mkdirs();
        }

        File dest = new File(destDir);
        if (!dest.exists()) {
            dest.mkdirs();
        }
    }
}
