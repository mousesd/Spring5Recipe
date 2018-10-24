package net.homenet.s4;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"SameParameterValue", "SpringJavaAutowiredMembersInspection"})
class SequenceGenerator {
    private PrefixGenerator prefixGenerator;
    private String suffix;
    private int initial;
    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    void setInitial(int initial) {
        this.initial = initial;
    }

    String getSequence() {
        return prefixGenerator.getPrefix()
            + (initial + counter.getAndIncrement())
            + suffix;
    }
}

