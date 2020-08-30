package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

public class ConstantNameAndTypeInfo extends Constant {
    /**
     * u2
     */
    private byte[] index1;
    private short index1Value;
    private Constant constant1;
    /**
     * u2
     */
    private byte[] index2;
    private short index2Value;
    private Constant constant2;

    public ConstantNameAndTypeInfo(byte[] index1, byte[] index2) {
        this.tag = ConstantTypes.CONSTANT_NAMEANDTYPE_INFO;
        this.index1 = index1;
        this.index1Value = ByteUtils.u2Short(index1);
        this.index2 = index2;
        this.index2Value = ByteUtils.u2Short(index2);
    }

    @Override
    public void nest(List<Constant> constants) {
        this.constant1 = constants.get(index1Value - 1);
        this.constant2 = constants.get(index2Value - 1);
    }

    @Override
    public String toString() {
        return "ConstantNameAndTypeInfo{" +
                "index1=" + Arrays.toString(index1) +
                ", index1Value=" + index1Value +
                ", constant1=" + constant1 +
                ", index2=" + Arrays.toString(index2) +
                ", index2Value=" + index2Value +
                ", constant2=" + constant2 +
                ", tag=" + tag +
                '}';
    }
}
