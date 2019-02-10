package net.homenet.configuration;

import net.homenet.FileCopier;
import net.homenet.FileCopierImpl;
import net.homenet.FileReplicator;
import net.homenet.FileReplicatorImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import javax.annotation.PostConstruct;
import javax.management.remote.JMXConnectorServer;
import java.io.File;
import java.rmi.registry.Registry;
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
    public MBeanServerFactoryBean mBeanServerFactoryBean() {
        MBeanServerFactoryBean mBeanServer = new MBeanServerFactoryBean();
        mBeanServer.setLocateExistingServerIfPossible(true);
        return mBeanServer;
    }

    @Bean
    public MBeanExporter mBeanExporter(MBeanServerFactoryBean mBeanServerFactoryBean, FileReplicator fileReplicator) {
        Map<String, Object> beans = new HashMap<>();
        beans.put("net.homenet:name=fileReplicator", fileReplicator);

        MBeanExporter exporter = new MBeanExporter();
        exporter.setBeans(beans);
        exporter.setServer(mBeanServerFactoryBean.getObject());
        return exporter;
    }

    @Bean
    public FactoryBean<Registry> rmiRegistry() {
        return new RmiRegistryFactoryBean();
    }

    @Bean
    @DependsOn("rmiRegistry")
    public FactoryBean<JMXConnectorServer> connectorServer() {
        ConnectorServerFactoryBean connectorServer = new ConnectorServerFactoryBean();
        connectorServer.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator");
        return connectorServer;
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
