package com.marstalk.jdk8.jvm.customclassparser.constant;

import java.util.Arrays;
import java.util.List;

public class ConstantFloatInfo extends Constant{
    /**
     * u4
     */
    private byte[] bytes;
    private float value;

    public ConstantFloatInfo(byte[] bytes) {
        this.tag = ConstantTypes.CONSTANT_FLOAT_INFO;
        this.bytes = bytes;
        this.value = Float.intBitsToFloat(bytes[0] << 24 | bytes[1] << 16 | bytes[2] << 8 | bytes[3]);
    }

    @Override
    public String toString() {
        return "ConstantFloatInfo{" +
                "bytes=" + Arrays.toString(bytes) +
                ", value=" + value +
                '}';
    }

    @Override
    public void nest(List<Constant> constants) {

    }
}
