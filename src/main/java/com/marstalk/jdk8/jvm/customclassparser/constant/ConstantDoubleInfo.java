package com.marstalk.jdk8.jvm.customclassparser.constant;

import java.util.Arrays;
import java.util.List;

public class ConstantDoubleInfo extends Constant {
    /**
     * u8
     */
    private byte[] bytes;
    private double value;

    public ConstantDoubleInfo(byte[] bytes) {
        this.tag = ConstantTypes.CONSTANT_DOUBLE_INFO;
        this.bytes = bytes;
        this.value = Double.longBitsToDouble(bytes[0] << 56
                | bytes[1] << 48
                | bytes[2] << 40
                | bytes[3] << 32
                | bytes[4] << 24
                | bytes[5] << 16
                | bytes[6] << 8
                | bytes[7]);
    }

    @Override
    public void nest(List<Constant> constants) {

    }

    @Override
    public String toString() {
        return "ConstantDoubleInfo{" +
                "bytes=" + Arrays.toString(bytes) +
                ", value=" + value +
                ", tag=" + tag +
                '}';
    }
}
