package net.homenet;

public class Operands {
    private final Number x;
    private final Number y;

    public Operands(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    public Number getX() {
        return x;
    }

    public Number getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Operands [x=%d, y=%d", x, y);
    }
}
