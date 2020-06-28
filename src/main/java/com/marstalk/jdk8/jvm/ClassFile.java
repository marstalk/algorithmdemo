package com.marstalk.jdk8.jvm;

import java.util.Arrays;

public class ClassFile {
    private int magic;
    private short minorVersion;
    private short majorVersion;

    private int constantPoolCount;
    private CPInfo[] constantPool;

    private int accessFlags;
    private int thisClass;
    private int interfacesCount;
    private Interface[] interfaces;
    private int fieldsCount;
    private FieldInfo[] fields;
    private int methodsCount;
    private MethodInfo[] methods;
    private int attributesCounts;
    private AttributeInfo[] attributes;

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public short getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(short minorVersion) {
        this.minorVersion = minorVersion;
    }

    public short getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(short majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public CPInfo[] getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(CPInfo[] constantPool) {
        this.constantPool = constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public void setThisClass(int thisClass) {
        this.thisClass = thisClass;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public Interface[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Interface[] interfaces) {
        this.interfaces = interfaces;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public FieldInfo[] getFields() {
        return fields;
    }

    public void setFields(FieldInfo[] fields) {
        this.fields = fields;
    }

    public int getMethodsCount() {
        return methodsCount;
    }

    public void setMethodsCount(int methodsCount) {
        this.methodsCount = methodsCount;
    }

    public MethodInfo[] getMethods() {
        return methods;
    }

    public void setMethods(MethodInfo[] methods) {
        this.methods = methods;
    }

    public int getAttributesCounts() {
        return attributesCounts;
    }

    public void setAttributesCounts(int attributesCounts) {
        this.attributesCounts = attributesCounts;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ClassFile{" +
                "magic=" + magic +
                ", minorVersion=" + minorVersion +
                ", majorVersion=" + majorVersion +
                ", constantPoolCount=" + constantPoolCount +
                ", constantPool=" + Arrays.toString(constantPool) +
                ", accessFlags=" + accessFlags +
                ", thisClass=" + thisClass +
                ", interfacesCount=" + interfacesCount +
                ", interfaces=" + Arrays.toString(interfaces) +
                ", fieldsCount=" + fieldsCount +
                ", fields=" + Arrays.toString(fields) +
                ", methodsCount=" + methodsCount +
                ", methods=" + Arrays.toString(methods) +
                ", attributesCounts=" + attributesCounts +
                ", attributes=" + Arrays.toString(attributes) +
                '}';
    }
}
