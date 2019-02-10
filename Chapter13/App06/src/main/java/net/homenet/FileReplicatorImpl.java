package net.homenet;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.io.File;
import java.io.IOException;

@ManagedResource(description = "File replicator")
public class FileReplicatorImpl implements FileReplicator {
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;

    @Override
    @ManagedAttribute(description = "Get source directory")
    public String getSrcDir() {
        return srcDir;
    }

    @Override
    @ManagedAttribute(description = "Set source directory")
    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

    @Override
    @ManagedAttribute(description = "Get destination directory")
    public String getDestDir() {
        return destDir;
    }

    @Override
    @ManagedAttribute(description = "Set destination directory")
    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    @Override
    public FileCopier getFileCopier() {
        return fileCopier;
    }

    @Override
    public void setFileCopier(FileCopier fileCopier) {
        this.fileCopier = fileCopier;
    }

    @Override
    @ManagedOperation(description = "Replicate files")
    public synchronized void replicate() throws IOException {
        System.out.println("FileReplicatorImpl.replicate()");
        File[] files = new File(srcDir).listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                fileCopier.copyFile(srcDir, destDir, file.getName());
            }
        }
    }
}
