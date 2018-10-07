package net.homenet.s2;

public interface ISequenceDao {
    Sequence getSequence(String sequenceId);
    int getNextValue(String sequenceId);
}
