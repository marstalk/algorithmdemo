package com.marstalk.jdk8.jvm.customclassparser;

import com.marstalk.jdk8.jvm.customclassparser.constant.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Human readable object refers to specific class file.
 * For JDK8
 */
public class Clazz {
    private int magic;
    private short minorVersion;
    private short majorVersion;

    private int constantPoolCount;
    private List<Constant> constants = new ArrayList<>();

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

    public Clazz() {
    }

    public void addConstant(Constant constant) {
        this.constants.add(constant);
    }

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
        return "Clazz{" +
                "magic=" + magic +
                ", minorVersion=" + minorVersion +
                ", majorVersion=" + majorVersion +
                ", constantPoolCount=" + constantPoolCount +
                ", constants=" + constants +
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

    public List<Constant> getConstants() {
        return constants;
    }

    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }
}
