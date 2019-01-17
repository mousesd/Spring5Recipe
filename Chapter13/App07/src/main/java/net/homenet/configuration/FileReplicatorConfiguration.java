package net.homenet.configuration;

import net.homenet.FileCopier;
import net.homenet.FileCopierImpl;
import net.homenet.FileReplicator;
import net.homenet.FileReplicatorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SuppressWarnings("Duplicates")
@Configuration
@EnableScheduling
public class FileReplicatorConfiguration implements SchedulingConfigurer {
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
    public Executor executor() {
        return Executors.newScheduledThreadPool(10);
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

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(executor());
    }
}
