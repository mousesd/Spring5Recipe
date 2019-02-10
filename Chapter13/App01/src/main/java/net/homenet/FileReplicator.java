package net.homenet;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.io.IOException;

public interface FileReplicator {
    String getSrcDir();
    void setSrcDir(String srcDir);
    String getDestDir();
    void setDestDir(String destDir);
    FileCopier getFileCopier();
    void setFileCopier(FileCopier fileCopier);
    void replicate() throws IOException;
}
