package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

public class ConstantMethodTypeInfo extends Constant {
    /**
     * u2
     */
    private byte[] descrptorIndex;
    private short descriptorIndexValue;
    private Constant constantUtf8Info;

    public ConstantMethodTypeInfo(byte[] descrptorIndex) {
        this.tag = ConstantTypes.CONSTANT_METHODTYPE_INFO;
        this.descrptorIndex = descrptorIndex;
        this.descriptorIndexValue = ByteUtils.u2Short(descrptorIndex);
    }

    @Override
    public void nest(List<Constant> constants) {
        this.constantUtf8Info = constants.get(descriptorIndexValue - 1);
    }

    @Override
    public String toString() {
        return "ConstantMethodTypeInfo{" +
                "descrptorIndex=" + Arrays.toString(descrptorIndex) +
                ", descriptorIndexValue=" + descriptorIndexValue +
                ", constantUtf8Info=" + constantUtf8Info +
                ", tag=" + tag +
                '}';
    }
}
