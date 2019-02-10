package net.homenet.configuration;

import net.homenet.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import javax.annotation.PostConstruct;
import javax.management.NotificationListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
    public MBeanExporter mBeanExporter() {
        Map<String, NotificationListener> mappings = new HashMap<>();
        mappings.put("bean:name=fileReplicator,type=FileReplicatorImpl", new ReplicationNotificationListener());

        AnnotationMBeanExporter exporter = new AnnotationMBeanExporter();
        exporter.setDefaultDomain("bean");
        exporter.setNotificationListenerMappings(mappings);
        return exporter;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @PostConstruct
    public void verifyDirectories() {
        File src = new File(srcDir);
        File dest = new File(destDir);

        if (!src.exists()) {
            src.mkdir();
        }

        if (!dest.exists()) {
            dest.mkdir();
        }
    }
}
