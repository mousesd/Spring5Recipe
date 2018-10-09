package net.homenet;

import org.springframework.beans.factory.BeanNameAware;

import java.io.*;
import java.util.Date;

@SuppressWarnings({"ResultOfMethodCallIgnored", "SameParameterValue", "Duplicates", "unused"})
class Cashier implements BeanNameAware {
    private String fileName;
    private String path;
    private BufferedWriter writer;

    @Override
    public void setBeanName(String name) {
        fileName = name;
    }

    void setPath(String path) {
        this.path = path;
    }

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

    void closeFile() throws IOException {
        writer.close();
    }
}
