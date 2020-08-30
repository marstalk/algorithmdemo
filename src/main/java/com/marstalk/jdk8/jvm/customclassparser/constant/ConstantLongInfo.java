package com.marstalk.jdk8.jvm.customclassparser.constant;

import java.util.Arrays;
import java.util.List;

public class ConstantLongInfo extends Constant{
    /**
     * u8
     */
    private byte[] bytes;
    private long value;

    public ConstantLongInfo(byte[] bytes) {
        this.tag = ConstantTypes.CONSTANT_LONG_INFO;
        this.bytes = bytes;
        this.value = 0;
        for (int i = 0; i < bytes.length; i++) {
            value += (value << 8) + (bytes[i] & 0xff);
        }
    }

    @Override
    public String toString() {
        return "ConstantLongInfo{" +
                "bytes=" + Arrays.toString(bytes) +
                ", value=" + value +
                '}';
    }

    @Override
    public void nest(List<Constant> constants) {

    }
}
