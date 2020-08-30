package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

public class ConstantStringInfo extends Constant {
    private byte[] index;
    private short indexValue;
    private Constant constantUTF8Info;

    public ConstantStringInfo(byte[] index) {
        this.tag = ConstantTypes.CONSTANT_STRING_INFO;
        this.index = index;
        this.indexValue = ByteUtils.u2Short(index);
    }

    @Override
    public void nest(List<Constant> constants) {
        this.constantUTF8Info = constants.get(indexValue - 1);
    }

    @Override
    public String toString() {
        return "ConstantStringInfo{" +
                "index=" + Arrays.toString(index) +
                ", indexValue=" + indexValue +
                ", constantUTF8Info=" + constantUTF8Info +
                ", tag=" + tag +
                '}';
    }
}
