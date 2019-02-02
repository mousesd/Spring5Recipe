package net.homenet;

import org.springframework.integration.annotation.Splitter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;

public class CustomerFilesSplitter {
    @Splitter
    public Collection<String> splitCustomerFiles(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }
}
