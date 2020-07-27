package com.marstalk.jdk8.objectlayout;

import org.openjdk.jol.info.ClassLayout;
import org.testng.annotations.Test;

/**
 * 所有的输出，都用的是这个版本的JDK：
 * $ java -version
 * java version "1.8.0_221"
 * Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.221-b11, mixed mode)
 */
public class InstanceLayout {
    Foo foo;
    Foo foo2 = new Foo();
    String str = "hello, java";
    short s;
    int i;
    long l;

    float f;
    double d;

    byte b;
    char c;
    boolean bool;

    @Test
    public void basicTypes() {
        System.out.println(ClassLayout.parseInstance(new InstanceLayout()).toPrintable());
        /**
         * com.marstalk.jdk8.objectlayout.InstanceLayout object internals:
         *  OFFSET  SIZE                                 TYPE DESCRIPTION                               VALUE
         *       0     4                                      (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                                      (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                      (object header)                           9d 4a 01 f8 (10011101 01001010 00000001 11111000) (-134133091)
         *      12     4                                  int InstanceLayout.i                          0
         *      16     8                                 long InstanceLayout.l                          0
         *      24     8                               double InstanceLayout.d                          0.0
         *      32     4                                float InstanceLayout.f                          0.0
         *      36     2                                short InstanceLayout.s                          0
         *      38     2                                 char InstanceLayout.c
         *      40     1                                 byte InstanceLayout.b                          0
         *      41     1                              boolean InstanceLayout.bool                       false //boolean类型，1个字节，
         *      42     2                                      (alignment/padding gap)
         *      44     4   com.marstalk.jdk8.objectlayout.Foo InstanceLayout.foo                        null //引用类型，null，没有指针。
         *      48     4   com.marstalk.jdk8.objectlayout.Foo InstanceLayout.foo2                       (object)//引用类型，4个字节，指向foo2对象。
         *      52     4                     java.lang.String InstanceLayout.str                        (object)//引用类型，4个字节，指向string对象。
         * Instance size: 56 bytes
         * Space losses: 2 bytes internal + 0 bytes external = 2 bytes total
         */
    }

    @Test
    public void object(){

        //一个object对象，在64位虚拟机中至少占用16字节。其中有4个字节是对齐部分，使得对象占用内存大小是8的倍数。
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        /**
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
    }

    @Test
    public void booleanWrapper(){
        //包装类，有个属性boolean，占用一个字节，然后再占用3个字节的对齐，最终占用的还是16个字节。
        System.out.println(ClassLayout.parseInstance(new Boolean(true)).toPrintable());
        /**
         * java.lang.Boolean object internals:
         *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         *       0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4           (object header)                           ca 20 00 f8 (11001010 00100000 00000000 11111000) (-134209334)
         *      12     1   boolean Boolean.value                             true
         *      13     3           (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         */
    }


    @Test
    public void string(){
        //字符串String底层使用char[]来实现，对象头+只想char数组的指针（4个字节），还有4个字节的hash值。
        System.out.println(ClassLayout.parseInstance("hello").toPrintable());
        /**
         * java.lang.String object internals:
         *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
         *       0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4          (object header)                           da 02 00 f8 (11011010 00000010 00000000 11111000) (-134216998)
         *      12     4   char[] String.value                              [h, e, l, l, o]
         *      16     4      int String.hash                               0
         *      20     4          (loss due to the next object alignment)
         * Instance size: 24 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */

        System.out.println(ClassLayout.parseInstance("java").toPrintable());
        /**
         * java.lang.String object internals:
         *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
         *       0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4          (object header)                           da 02 00 f8 (11011010 00000010 00000000 11111000) (-134216998)
         *      12     4   char[] String.value                              [j, a, v, a]
         *      16     4      int String.hash                               0
         *      20     4          (loss due to the next object alignment)
         * Instance size: 24 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */


        //数组对象，对象头中多了4个字节来表示数组的长度。
        System.out.println(ClassLayout.parseInstance(new char[]{'j', 'a', 'v', 'a'}).toPrintable());
        /**
         * [C object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           41 00 00 f8 (01000001 00000000 00000000 11111000) (-134217663)
         *      12     4        (object header)                           04 00 00 00 (00000100 00000000 00000000 00000000) (4)
         *      16     8   char [C.<elements>                             N/A
         * Instance size: 24 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
    }

    @Test
    public void longArray(){
        //数组对象，对象头中除了标配的8字节markwork，4字节指针指向类型外，还有4字节用来表示数组的长度。8+4+4 = 16
        //实例数据长度就是每个long的长度8*个数4 = 32.
        //总共是16+32 = 48
        //补齐：因为48=8*6，刚好是8的倍数，所以不需要补齐。
        System.out.println(ClassLayout.parseInstance(new long[]{3L, 4L, 5L, 7L}).toPrintable());
        /**
         * [J object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           a9 01 00 f8 (10101001 00000001 00000000 11111000) (-134217303)
         *      12     4        (object header)                           04 00 00 00 (00000100 00000000 00000000 00000000) (4)
         *      16    32   long [J.<elements>                             N/A
         * Instance size: 48 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
    }


    @Test
    public void i(){
        //自动装箱为Integer对象。
        System.out.println(ClassLayout.parseInstance(6666).toPrintable());
        /**
         * java.lang.Integer object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           ac 22 00 f8 (10101100 00100010 00000000 11111000) (-134208852)
         *      12     4    int Integer.value                             6666
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
    }


}
