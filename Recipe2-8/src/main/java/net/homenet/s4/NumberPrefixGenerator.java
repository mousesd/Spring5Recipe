package net.homenet.s4;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberPrefixGenerator implements PrefixGenerator {
    @Override
    public String getPrefix() {
        Random random = new Random();
        int value = random.nextInt(100);
        return String.format("%03d", value);
    }
}
