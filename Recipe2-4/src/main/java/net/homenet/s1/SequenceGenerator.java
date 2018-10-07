package net.homenet.s1;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"SameParameterValue", "SpringJavaAutowiredMembersInspection"})
class SequenceGenerator {
    @Resource(name = "datePrefixGenerator")
    private PrefixGenerator prefixGenerator;
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
        return prefixGenerator.getPrefix()
            + (initial + counter.getAndIncrement())
            + suffix;
    }
}

