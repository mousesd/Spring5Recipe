package net.homenet;

public class CounterImpl implements Counter {
    private int counter;

    @Override
    public void increase() {
        counter++;
    }

    @Override
    public int getCount() {
        return counter;
    }
}
