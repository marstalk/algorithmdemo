package com.marstalk.jdk8.jvm.customclassparser;

import com.marstalk.jdk8.jvm.customclassparser.constant.Constant;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantClassInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantDoubleInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantFieldRefInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantFloatInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantIntegerInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantInterfaceMethodRefInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantInvokeDynamicInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantLongInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantMethodHandleInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantMethodRefInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantMethodTypeInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantNameAndTypeInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantStringInfo;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantTypes;
import com.marstalk.jdk8.jvm.customclassparser.constant.ConstantUtf8Info;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 解析class文件
 */
public class ClassParser {
    private static String hexStr = "0123456789abcdef";

    public Class parse(byte[] bytes) {
        return null;
    }

    public Clazz parse(String classFilePath) {
        Clazz clazz = new Clazz();

        try (FileChannel fileChannel = FileChannel.open(Paths.get(classFilePath), StandardOpenOption.READ)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            //TODO 怎么保证这个byteBuffer已经读完了这个文件。

            fileChannel.read(byteBuffer);
            byteBuffer.flip();

            //magic number: u4
            byte[] bys = new byte[4];
            byteBuffer.get(bys);
            String magicStr = byte2HexStr(bys);
            if (!"cafebabe".equalsIgnoreCase(magicStr)) {
                System.out.println("This is not a class file " + classFilePath);
                return null;
            }
            int magicNum = getInt(bys, 0);
            clazz.setMagic(magicNum);


            //minor version u2
            bys = new byte[2];
            byteBuffer.get(bys);
            short minorVersion = getShort(bys, 0);
            clazz.setMinorVersion(minorVersion);

            //major version
            bys = new byte[2];
            byteBuffer.get(bys);
            short majorVersion = getShort(bys, 0);
            clazz.setMajorVersion(majorVersion);


            //constant pool info
            handleCPInfo(clazz, byteBuffer);

            //access flag
            bys = new byte[2];
            byteBuffer.get(bys);
            short accessFlag = ByteUtils.u2Short(bys);

            //this class
            bys = new byte[2];
            byteBuffer.get(bys);
            short thisClazz = ByteUtils.u2Short(bys);
            //Constant constant = clazz.getConstants().get(thisClazz);
            //System.out.println("thisClass " + constant);


            byteBuffer.clear();
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleCPInfo(Clazz clazz, ByteBuffer byteBuffer) {
        //constant pool size
        byte[] b = new byte[2];
        byteBuffer.get(b);
        short constantPoolCount = getShort(b, 0);
        clazz.setConstantPoolCount(constantPoolCount);

        while (constantPoolCount-- > 0) {
            b = new byte[1];
            byteBuffer.get(b);
            int tag = b[0];
            if (tag == ConstantTypes.CONSTANT_UTF8_INFO) {
                byte[] lengthBytes = new byte[2];
                byteBuffer.get(lengthBytes);
                short length = getShort(lengthBytes, 0);
                b = new byte[length];
                byteBuffer.get(b);
                Constant constantUtf8Info = new ConstantUtf8Info(lengthBytes, b);
                clazz.addConstant(constantUtf8Info);
            } else if (tag == ConstantTypes.CONSTANT_INTGER_INFO) {
                b = new byte[4];
                byteBuffer.get(b);
                Constant constantIntegerInfo = new ConstantIntegerInfo(b);
                clazz.addConstant(constantIntegerInfo);
            } else if (tag == ConstantTypes.CONSTANT_FLOAT_INFO) {
                b = new byte[4];
                byteBuffer.get(b);
                Constant constantFloatInfo = new ConstantFloatInfo(b);
                clazz.addConstant(constantFloatInfo);
            } else if (tag == ConstantTypes.CONSTANT_LONG_INFO) {
                b = new byte[8];
                byteBuffer.get(b);
                Constant constantLongInfo = new ConstantLongInfo(b);
                clazz.addConstant(constantLongInfo);
            } else if (tag == ConstantTypes.CONSTANT_DOUBLE_INFO) {
                b = new byte[8];
                byteBuffer.get(b);
                Constant constantDoubleInfo = new ConstantDoubleInfo(b);
                clazz.addConstant(constantDoubleInfo);
            } else if (tag == ConstantTypes.CONSTANT_CLASS_INFO) {
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantClassInfo = new ConstantClassInfo(b);
                clazz.addConstant(constantClassInfo);
            } else if (tag == ConstantTypes.CONSTANT_STRING_INFO) {
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantStringInfo = new ConstantStringInfo(b);
                clazz.addConstant(constantStringInfo);
            } else if (tag == ConstantTypes.CONSTANT_FIELDREF_INFO) {
                byte[] b2 = new byte[2];
                byteBuffer.get(b2);
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantFieldRefInfo = new ConstantFieldRefInfo(b2, b);
                clazz.addConstant(constantFieldRefInfo);
            } else if (tag == ConstantTypes.CONSTANT_METHODREF_INFO) {
                byte[] b2 = new byte[2];
                byteBuffer.get(b2);
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantMethodRef = new ConstantMethodRefInfo(b2, b);
                clazz.addConstant(constantMethodRef);
            } else if (tag == ConstantTypes.CONSTANT_INTEFACE_METHODREF_INFO) {
                byte[] b2 = new byte[2];
                byteBuffer.get(b2);
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantInterfaceMethodRefInfo = new ConstantInterfaceMethodRefInfo(b2, b);
                clazz.addConstant(constantInterfaceMethodRefInfo);
            } else if (tag == ConstantTypes.CONSTANT_NAMEANDTYPE_INFO) {
                byte[] b2 = new byte[2];
                byteBuffer.get(b2);
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantNameAndType = new ConstantNameAndTypeInfo(b2, b);
                clazz.addConstant(constantNameAndType);
            } else if (tag == ConstantTypes.CONSTANT_METHODHANDLE_INFO) {
                byte[] b1 = new byte[1];
                byteBuffer.get(b1);
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantMethodHandleInfo = new ConstantMethodHandleInfo(b1, b);
                clazz.addConstant(constantMethodHandleInfo);
            } else if (tag == ConstantTypes.CONSTANT_METHODTYPE_INFO) {
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantMethodTypeInfo = new ConstantMethodTypeInfo(b);
                clazz.addConstant(constantMethodTypeInfo);
            } else if (tag == ConstantTypes.CONSTANT_INVOKEDAYNAMIC_INFO) {
                byte[] b2 = new byte[2];
                byteBuffer.get(b2);
                b = new byte[2];
                byteBuffer.get(b);
                Constant constantInvokeDynamicInfo = new ConstantInvokeDynamicInfo(b2, b);
                clazz.addConstant(constantInvokeDynamicInfo);
            }
        }
        clazz.getConstants().forEach(constant -> constant.nest(clazz.getConstants()));

    }

    private int byte2Int(byte[] bytes) {
        String s = byte2HexStr(bytes);
        return Integer.parseInt(s, 16);
    }

    /**
     * 四个字节
     *
     * @param bb
     * @param index
     * @return
     */
    public static int getInt(byte[] bb, int index) {
        return (int) ((((bb[index + 0] & 0xff) << 24)
                | ((bb[index + 1] & 0xff) << 16)
                | ((bb[index + 2] & 0xff) << 8) | ((bb[index + 3] & 0xff) << 0)));
    }

    /**
     * 两个字节
     *
     * @param b
     * @param index
     * @return
     */
    public short getShort(byte[] b, int index) {
//        String s = byte2HexStr(b);
//        System.out.println(s);
        return (short) (((b[index + 0] << 8) | b[index + 1] & 0xff));
    }


    /**
     * 字节数组转为16进制字符。
     *
     * @param bytes
     * @return
     */
    private String byte2HexStr(byte[] bytes) {

        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            byte abyte = bytes[i];
//            System.out.println(abyte);
            //一个字节（byte）的本质上是8个二进制位。
            //转成16进制字符的话，每四位可以表示一个，因为4位的最大值刚好是F。
            //清空低4位，然后右移4位，等到高4位的值，然后应为charAt的入参是int类型，那么高4位的值就会转为等价的int类型（十进制）的值，
            //那么就有可能是从0~15，然后因为要转成字符，那么就是对应的"0"到"F"。
            //字节高4位
            hex = String.valueOf(hexStr.charAt((abyte & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(abyte & 0x0F));
            result += hex;
        }
        return result;
    }
}
