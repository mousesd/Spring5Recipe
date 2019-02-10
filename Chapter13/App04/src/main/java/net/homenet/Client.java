package net.homenet;

import net.homenet.configuration.JmxClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JmxClientConfiguration.class);

        //# 1.Access remote MBeans through an MBean server connection
        //ObjectName mBeanName = new ObjectName("net.homenet:name=fileReplicator");
        //MBeanServerConnection mBeanServerConnection = context.getBean(MBeanServerConnection.class);
        //mBeanServerConnection.addNotificationListener(mBeanName, new ReplicationNotificationListener(), null, null);
        //
        //String srcDir = (String) mBeanServerConnection.getAttribute(mBeanName, "SrcDir");
        //mBeanServerConnection.setAttribute(mBeanName, new Attribute("DestDir", srcDir + "_backup"));
        //mBeanServerConnection.invoke(mBeanName, "replicate", new Object[]{}, new String[]{});

        //# 2.Access remote MBeans through an MBean proxy
        FileReplicator fileReplicator = context.getBean(FileReplicator.class);
        String srcDir = fileReplicator.getSrcDir();
        fileReplicator.setDestDir(srcDir + "_backup");
        fileReplicator.replicate();
    }
}
