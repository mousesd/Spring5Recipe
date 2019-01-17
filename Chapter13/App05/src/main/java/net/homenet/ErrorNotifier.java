package net.homenet;

public interface ErrorNotifier {
    void notifyCopyError(String srcDir, String destDir, String filename);
}
