package net.homenet.s2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Date;

@SuppressWarnings({"ResultOfMethodCallIgnored", "SameParameterValue", "Duplicates", "unused"})
@Component
class Cashier {
    @Value("checkout")
    private String fileName;
    @Value("/Users/mousesd/Documents/")
    private String path;
    private BufferedWriter writer;

    void setFileName(String fileName) {
        this.fileName = fileName;
    }

    void setPath(String path) {
        this.path = path;
    }

    @PostConstruct
    void openFile() throws IOException {
        File targetDir = new File(path);
        if (!targetDir.exists())
            targetDir.mkdir();

        File checkoutFile = new File(path, fileName + ".txt");
        if (!checkoutFile.exists())
            checkoutFile.createNewFile();

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile, true)));
    }

    void checkout(ShoppingCart cart) throws IOException {
        writer.write(new Date() + "\t" + cart.getProducts() + "\r\n");
        writer.flush();
    }

    @PreDestroy
    void closeFile() throws IOException {
        writer.close();
    }
}
