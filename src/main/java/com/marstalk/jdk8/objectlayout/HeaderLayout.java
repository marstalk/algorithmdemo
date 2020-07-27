package com.marstalk.jdk8.objectlayout;

import org.openjdk.jol.info.ClassLayout;
import org.testng.annotations.Test;

/**
 * $ java -version
 * java version "1.8.0_221"
 * Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.221-b11, mixed mode)
 */
public class HeaderLayout {
    class Foo {
    }

    /**
     * 数组的对象头会多出4个字节，用来表述数组的长度。
     */
    @Test
    public void test() {
        System.out.println(ClassLayout.parseInstance(new Foo()).toPrintable());

        //为了验证对象头的class pointer是同一个。
        System.out.println(ClassLayout.parseInstance(new Foo()).toPrintable());

        //数组长度是6
        System.out.println(ClassLayout.parseInstance(new Foo[6]).toPrintable());
        /**
         * com.marstalk.jdk8.objectlayout.HeaderLayout$Foo object internals:
         *  OFFSET  SIZE                                          TYPE DESCRIPTION                               VALUE
         *       0     4                                               (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                                               (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                               (object header)                           a1 90 01 f8 (10100001 10010000 00000001 11111000) (-134115167)
         *      12     4   com.marstalk.jdk8.objectlayout.HeaderLayout Foo.this$0                                (object)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         *
         * com.marstalk.jdk8.objectlayout.HeaderLayout$Foo object internals:
         *  OFFSET  SIZE                                          TYPE DESCRIPTION                               VALUE
         *       0     4                                               (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                                               (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                               (object header)                           a1 90 01 f8 (10100001 10010000 00000001 11111000) (-134115167)
         *      12     4   com.marstalk.jdk8.objectlayout.HeaderLayout Foo.this$0                                (object)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         *
         * [Lcom.marstalk.jdk8.objectlayout.HeaderLayout$Foo; object internals:
         *  OFFSET  SIZE                                              TYPE DESCRIPTION                               VALUE
         *       0     4                                                   (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                                                   (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                                   (object header)                           3e 02 02 f8 (00111110 00000010 00000010 11111000) (-134086082)
         *      12     4                                                   (object header)                           06 00 00 00 (00000110 00000000 00000000 00000000) (6)
         *      16    24   com.marstalk.jdk8.objectlayout.HeaderLayout$Foo HeaderLayout$Foo;.<elements>              N/A
         * Instance size: 40 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
    }

    /**
     * 数组的对象头会多出4个字节，用来表述数组的长度。
     */
    @Test
    public void ArrayHeader() {
        System.out.println(ClassLayout.parseInstance(new Foo()).toPrintable());
        //数组长度初始化时0.
        System.out.println(ClassLayout.parseInstance(new Foo[]{}).toPrintable());
        /**
         * com.marstalk.jdk8.objectlayout.HeaderLayout$Foo object internals:
         *  OFFSET  SIZE                                          TYPE DESCRIPTION                               VALUE
         *       0     4                                               (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                                               (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                               (object header)                           a1 90 01 f8 (10100001 10010000 00000001 11111000) (-134115167)
         *      12     4   com.marstalk.jdk8.objectlayout.HeaderLayout Foo.this$0                                (object)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         *
         * [Lcom.marstalk.jdk8.objectlayout.HeaderLayout$Foo; object internals:
         *  OFFSET  SIZE                                              TYPE DESCRIPTION                               VALUE
         *       0     4                                                   (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                                                   (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                                   (object header)                           3e f2 01 f8 (00111110 11110010 00000001 11111000) (-134090178)
         *      12     4                                                   (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *      16     0   com.marstalk.jdk8.objectlayout.HeaderLayout$Foo HeaderLayout$Foo;.<elements>              N/A
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
    }

    @Test
    public void testHashCode(){
        class Bar {
            @Override public int hashCode() {
                return 3;
            }
        }
        System.out.println(ClassLayout.parseInstance(new Bar()).toPrintable());
    }

    @Test
    public void LockStatus(){
        Foo foo = new Foo();

        //1. 对象刚被初始化，是无锁状态
        System.out.println(ClassLayout.parseInstance(foo).toPrintable());
        synchronized (foo) {
            //当前线程是第一个来竞争的线程，持有了foo的锁，此刻是偏量锁，对象头中的markword记录着当前线程的ID。可以重入。
            System.out.println(ClassLayout.parseInstance(foo).toPrintable());
        }
    }

}
