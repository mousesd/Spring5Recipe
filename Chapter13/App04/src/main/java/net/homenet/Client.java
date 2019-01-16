package net.homenet;

import net.homenet.configuration.JmxClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public class Client {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JmxClientConfiguration.class);

        ObjectName mBeanName = new ObjectName("net.homenet:name=fileReplicator");

        MBeanServerConnection mBeanServerConnection = context.getBean(MBeanServerConnection.class);
        mBeanServerConnection.addNotificationListener(mBeanName, new ReplicationNotificationListener(), null, null);

        String srcDir = (String) mBeanServerConnection.getAttribute(mBeanName, "SrcDir");
        mBeanServerConnection.setAttribute(mBeanName, new Attribute("DestDir", srcDir + "_backup"));
        mBeanServerConnection.invoke(mBeanName, "replicate", new Object[]{}, new String[]{});
    }
}
