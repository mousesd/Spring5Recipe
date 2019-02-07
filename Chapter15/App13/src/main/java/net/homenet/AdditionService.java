package net.homenet;

public class AdditionService {
    public Number add(Operands ops) {
        return ops.getX().floatValue() + ops.getY().floatValue();
    }
}
