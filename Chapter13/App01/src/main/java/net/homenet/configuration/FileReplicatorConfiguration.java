package net.homenet.configuration;

import net.homenet.FileCopier;
import net.homenet.FileCopierImpl;
import net.homenet.FileReplicator;
import net.homenet.FileReplicatorImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
@EnableMBeanExport
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

    //# Use a MethodNameBasedMBeanInfoAssembler class
    //@Bean
    //public MBeanInfoAssembler mBeanInfoAssembler() {
    //    MethodNameBasedMBeanInfoAssembler assembler = new MethodNameBasedMBeanInfoAssembler();
    //    assembler.setManagedMethods("replicate");
    //    return assembler;
    //}

    //# Use a InterfaceBasedMBeanInfoAssembler class
    //@Bean
    //public MBeanInfoAssembler mBeanInfoAssembler() {
    //    InterfaceBasedMBeanInfoAssembler assembler = new InterfaceBasedMBeanInfoAssembler();
    //    assembler.setManagedInterfaces(FileReplicator.class);
    //    return assembler;
    //}

    //# Use a MetadataBeanInfoAssembler class
    //@Bean
    //public MBeanInfoAssembler mBeanInfoAssembler() {
    //    MetadataMBeanInfoAssembler assembler = new MetadataMBeanInfoAssembler();
    //    assembler.setAttributeSource(new AnnotationJmxAttributeSource());
    //    return assembler;
    //}

    //# @EnableMBeanExport 를 이용히 아래 Bean 이 필요 없어짐
    //@Bean
    //public MBeanExporter mBeanExporter(MBeanServerFactoryBean mBeanServer) {
    //    //# Use a MBeanExporter class
    //    //Map<String, Object> beanToExport = new HashMap<>();
    //    //beanToExport.put("bean:name=fileReplicator", fileReplicator);
    //
    //    //MBeanExporter exporter = new MBeanExporter();
    //    //exporter.setBeans(beanToExport);
    //    //exporter.setServer(mBeanServer.getObject());
    //    //exporter.setAssembler(assembler);
    //    //return exporter;
    //
    //    //# Use a AnnotationMBeanExporter class
    //    AnnotationMBeanExporter exporter = new AnnotationMBeanExporter();
    //    exporter.setServer(mBeanServer.getObject());
    //    return exporter;
    //}

    //# Register MBeans for remote access with RMI
    //@Bean
    //public FactoryBean<Registry> rmiRegistry() {
    //    return new RmiRegistryFactoryBean();
    //}
    //
    //@Bean
    //@DependsOn("rmiRegistry")
    //public FactoryBean<JMXConnectorServer> connectorServer() {
    //    ConnectorServerFactoryBean connectorServer = new ConnectorServerFactoryBean();
    //    connectorServer.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator");
    //    return connectorServer;
    //}

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
