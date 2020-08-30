package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

public class ConstantMethodHandleInfo extends Constant{
    /**
     * u1
     */
    private byte[] referenceKind;
    private short referenceKindValue;
    /**
     * u2
     */
    private byte[] referenceIndex;
    private short referenceIndexValue;
    private Constant constant;//TODO what kind of constant?

    public ConstantMethodHandleInfo(byte[] referenceKind, byte[] referenceIndex) {
        this.tag = ConstantTypes.CONSTANT_METHODHANDLE_INFO;
        this.referenceKind = referenceKind;
        this.referenceKindValue = ByteUtils.u1Short(referenceKind);
        this.referenceIndex = referenceIndex;
        this.referenceIndexValue = ByteUtils.u2Short(referenceIndex);
    }

    @Override
    public void nest(List<Constant> constants) {
        this.constant = constants.get(referenceIndexValue - 1);
    }

    @Override
    public String toString() {
        return "ConstantMethodHandleInfo{" +
                "referenceKind=" + Arrays.toString(referenceKind) +
                ", referenceKindValue=" + referenceKindValue +
                ", referenceIndex=" + Arrays.toString(referenceIndex) +
                ", referenceIndexValue=" + referenceIndexValue +
                ", constant=" + constant +
                ", tag=" + tag +
                '}';
    }
}
