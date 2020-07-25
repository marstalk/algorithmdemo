package com.marstalk.jdk8.jvm.customclassparser.constant;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ConstantUtf8Info extends Constant {
    /**
     * u2
     */
    private byte[] length;
    private int lengthValue;
    private byte[] bytes;
    private String value;

    public ConstantUtf8Info(byte[] length, byte[] bytes) {
        this.tag = ConstantTypes.CONSTANT_UTF8_INFO;
        this.length = length;
        this.lengthValue = length[0] << 8 | length[1];
        this.bytes = bytes;
        this.value = new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "ConstantUtf8Info{" +
                "tag=" + tag +
                ", length=" + Arrays.toString(length) +
                ", lengthValue=" + lengthValue +
                ", bytes=" + Arrays.toString(bytes) +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void nest(List<Constant> constants) {

    }
}
