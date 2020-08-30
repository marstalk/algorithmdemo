package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author shanzhonglaosou
 */
public class ConstantIntegerInfo extends Constant {
    /**
     * u4
     */
    private byte[] bytes;
    private int value;

    public ConstantIntegerInfo(byte[] bytes) {
        this.tag = ConstantTypes.CONSTANT_INTGER_INFO;
        this.bytes = bytes;
        this.value = ByteUtils.u4Int(bytes);
    }

    @Override
    public String toString() {
        return "ConstantIntegerInfo{" +
                "tag=" + tag +
                ", bytes=" + Arrays.toString(bytes) +
                ", value=" + value +
                '}';
    }

    @Override
    public void nest(List<Constant> constants) {

    }
}
