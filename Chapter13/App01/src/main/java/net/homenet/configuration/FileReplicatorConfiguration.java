package net.homenet.configuration;

import net.homenet.FileCopier;
import net.homenet.FileCopierImpl;
import net.homenet.FileReplicator;
import net.homenet.FileReplicatorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

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
