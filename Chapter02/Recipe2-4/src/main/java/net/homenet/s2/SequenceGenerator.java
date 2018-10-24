package net.homenet.s2;

import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"SameParameterValue", "SpringJavaAutowiredMembersInspection", "CdiInjectionPointsInspection"})
class SequenceGenerator {
    @Inject
    @NumberPrefixAnnotation
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

