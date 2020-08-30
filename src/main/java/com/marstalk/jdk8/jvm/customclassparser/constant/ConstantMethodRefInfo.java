package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

public class ConstantMethodRefInfo extends Constant {
    /**
     * u2
     */
    private byte[] index1;
    private short index1Value;
    private Constant constantClassInfo;
    /**
     * u2
     */
    private byte[] index2;
    private short index2Value;
    private Constant constantNameAndType;

    public ConstantMethodRefInfo(byte[] index1, byte[] index2) {
        this.tag = ConstantTypes.CONSTANT_METHODREF_INFO;
        this.index1 = index1;
        this.index1Value = ByteUtils.u2Short(index1);
        this.index2 = index2;
        this.index2Value = ByteUtils.u2Short(index2);
    }

    @Override
    public void nest(List<Constant> constants) {
        this.constantClassInfo = constants.get(index1Value - 1);
        this.constantNameAndType = constants.get(index2Value - 1);
    }

    @Override
    public String toString() {
        return "ConstantMethodRefInfo{" +
                "index1=" + Arrays.toString(index1) +
                ", index1Value=" + index1Value +
                ", constantClassInfo=" + constantClassInfo +
                ", index2=" + Arrays.toString(index2) +
                ", index2Value=" + index2Value +
                ", constantNameAndType=" + constantNameAndType +
                ", tag=" + tag +
                '}';
    }
}
