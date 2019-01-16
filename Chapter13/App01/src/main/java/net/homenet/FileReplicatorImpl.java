package net.homenet;

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
    public synchronized void replicate() throws IOException {
        File[] files = new File(srcDir).listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                fileCopier.copyFile(srcDir, destDir, file.getName());
            }
        }
    }
}
