package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

public class ConstantClassInfo extends Constant {
    /**
     * u2
     */
    private byte[] index;
    private short indexValue;
    private Constant constantUtf8Info;

    public ConstantClassInfo(byte[] bytes) {
        this.tag = ConstantTypes.CONSTANT_CLASS_INFO;
        this.index = bytes;
        this.indexValue = ByteUtils.u2Short(bytes);
    }

    @Override
    public void nest(List<Constant> constants) {
        this.constantUtf8Info = constants.get(indexValue - 1);
    }

    @Override
    public String toString() {
        return "ConstantClassInfo{" +
                "index=" + Arrays.toString(index) +
                ", indexValue=" + indexValue +
                ", constantUtf8Info=" + constantUtf8Info +
                ", tag=" + tag +
                '}';
    }
}
