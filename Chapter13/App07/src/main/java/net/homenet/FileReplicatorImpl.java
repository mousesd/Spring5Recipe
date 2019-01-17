package net.homenet;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;

public class FileReplicatorImpl implements FileReplicator {
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;

    @Override
    public String getSrcDir() {
        return srcDir;
    }

    @Override
    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

    @Override
    public String getDestDir() {
        return destDir;
    }

    @Override
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
    //@Scheduled(fixedDelay = 10 * 1000)
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
