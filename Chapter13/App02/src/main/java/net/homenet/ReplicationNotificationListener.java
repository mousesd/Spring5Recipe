package net.homenet;

import javax.management.Notification;
import javax.management.NotificationListener;

public class ReplicationNotificationListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification.getType().startsWith("replication")) {
            System.out.println(notification.getSource() + " "
                + notification.getType() + " #"
                + notification.getSequenceNumber());
        }
    }
}
