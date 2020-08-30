package com.marstalk.jdk8.jvm.customclassparser.constant;

import com.marstalk.jdk8.jvm.customclassparser.ByteUtils;

import java.util.Arrays;
import java.util.List;

public class ConstantInvokeDynamicInfo extends Constant{
    /**
     * u2
     */
    private byte[] boostrapMethodAttrIndex;
    private short boostrapMethodAttrIndexValue;


    /**
     * u2
     */
    private byte[] nameAndTypeIndex;
    private short nameAndTypeIndexValue;
    private Constant constantNameAndType;

    public ConstantInvokeDynamicInfo(byte[] boostrapMethodAttrIndex, byte[] nameAndTypeIndex) {
        this.tag = ConstantTypes.CONSTANT_INVOKEDAYNAMIC_INFO;
        this.boostrapMethodAttrIndex = boostrapMethodAttrIndex;
        this.boostrapMethodAttrIndexValue = ByteUtils.u2Short(boostrapMethodAttrIndex);
        this.nameAndTypeIndex = nameAndTypeIndex;
        this.nameAndTypeIndexValue = ByteUtils.u2Short(nameAndTypeIndex);
    }

    @Override
    public void nest(List<Constant> constants) {
        //TODO 方法表
        this.constantNameAndType = constants.get(nameAndTypeIndexValue - 1);
    }

    @Override
    public String toString() {
        return "ConstantInvokeDynamicInfo{" +
                "boostrapMethodAttrIndex=" + Arrays.toString(boostrapMethodAttrIndex) +
                ", boostrapMethodAttrIndexValue=" + boostrapMethodAttrIndexValue +
                ", nameAndTypeIndex=" + Arrays.toString(nameAndTypeIndex) +
                ", nameAndTypeIndexValue=" + nameAndTypeIndexValue +
                ", constantNameAndType=" + constantNameAndType +
                ", tag=" + tag +
                '}';
    }
}
