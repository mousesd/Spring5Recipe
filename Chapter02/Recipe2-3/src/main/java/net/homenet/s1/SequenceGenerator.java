package net.homenet.s1;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator {
    private DatePrefixGenerator prefixGenerator;
    private String suffix;
    private int initial;
    private final AtomicInteger counter = new AtomicInteger();

    public void setPrefixGenerator(DatePrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public SequenceGenerator() { }

    public String getSequence() {
        String builder = prefixGenerator.getPrefix()
            + (initial + counter.getAndIncrement())
            + suffix;
        return builder;
    }
}
