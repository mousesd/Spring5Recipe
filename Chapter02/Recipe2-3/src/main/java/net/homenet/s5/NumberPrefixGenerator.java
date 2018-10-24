package net.homenet.s5;

import java.util.Random;

public class NumberPrefixGenerator implements PrefixGenerator {
    @Override
    public String getPrefix() {
        Random random = new Random();
        int value = random.nextInt(100);
        return String.format("%03d", value);
    }
}
