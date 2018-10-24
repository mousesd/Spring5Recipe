package net.homenet.s3;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("SameParameterValue")
class SequenceGenerator {
    @Autowired
    private PrefixGenerator[] prefixGenerators;
    private String suffix;
    private int initial;
    private final AtomicInteger counter = new AtomicInteger();

    void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    void setInitial(int initial) {
        this.initial = initial;
    }

    String getSequence() {
        StringBuilder builder = new StringBuilder();
        for (PrefixGenerator generator : prefixGenerators) {
            builder.append(generator.getPrefix());
            builder.append("-");
        }
        builder.append(initial)
            .append(counter.getAndIncrement())
            .append(suffix);
        return builder.toString();
    }
}

