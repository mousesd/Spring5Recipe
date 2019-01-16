package net.homenet;

import net.homenet.configuration.FileReplicatorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.Descriptor;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.*;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationConfigApplicationContext(FileReplicatorConfiguration.class);
        FileReplicator fileReplicator = context.getBean(FileReplicator.class);

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("bean:name=fileReplicator");

        RequiredModelMBean mBean = new RequiredModelMBean();
        mBean.setManagedResource(fileReplicator, "objectReference");

        Descriptor srcDirDescriptor = new DescriptorSupport("name=SrcDir"
            , "descriptorType=attribute", "getMethod=getSrcDir", "setMethod=setSrcDir");
        ModelMBeanAttributeInfo srcDirInfo = new ModelMBeanAttributeInfo("SrcDir"
            , "java.lang.String"
            , "Source directory"
            , true
            , true
            , false
            , srcDirDescriptor);
        Descriptor destDirDescriptor = new DescriptorSupport("name=DestDir"
            , "descriptorType=attribute", "getMethod=getDestDir", "setMethod=setDestDir");
        ModelMBeanAttributeInfo destDirInfo = new ModelMBeanAttributeInfo("DestDir"
            , "java.lang.String"
            , "Destination directory"
            , true
            , true
            , false
            , destDirDescriptor);

        ModelMBeanOperationInfo getSrcDirInfo = new ModelMBeanOperationInfo("Get source directory"
            , FileReplicator.class.getMethod("getSrcDir"));
        ModelMBeanOperationInfo setSrcDirInfo = new ModelMBeanOperationInfo("Set source directory"
            , FileReplicator.class.getMethod("setSrcDir", String.class));
        ModelMBeanOperationInfo getDestDirInfo = new ModelMBeanOperationInfo("Get destination directory"
            , FileReplicator.class.getMethod("getDestDir"));
        ModelMBeanOperationInfo setDestDirInfo = new ModelMBeanOperationInfo("Set destination directory"
            , FileReplicator.class.getMethod("setDestDir", String.class));
        ModelMBeanOperationInfo replicatorInfo = new ModelMBeanOperationInfo("Replicate files"
            , FileReplicator.class.getMethod("replicate"));
        ModelMBeanInfo mBeanInfo = new ModelMBeanInfoSupport("FileReplicator"
            , "File replicator"
            , new ModelMBeanAttributeInfo[]{ srcDirInfo, destDirInfo }
            , null
            , new ModelMBeanOperationInfo[]{ getSrcDirInfo, setSrcDirInfo, getDestDirInfo, setDestDirInfo, replicatorInfo }
            , null);
        mBean.setModelMBeanInfo(mBeanInfo);
        mBeanServer.registerMBean(mBean, objectName);

        System.in.read();
    }
}
