package net.homenet;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.io.IOException;

@ManagedResource(description = "File replicator")
public interface FileReplicator {
    String getSrcDir();
    void setSrcDir(String srcDir);
    String getDestDir();
    void setDestDir(String destDir);
    FileCopier getFileCopier();
    void setFileCopier(FileCopier fileCopier);

    @ManagedOperation(description = "Replicate files")
    void replicate() throws IOException;
}
