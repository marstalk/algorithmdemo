package com.marstalk.jdk8.jvm.javaobjectlayout;

import org.openjdk.jol.info.ClassLayout;
import org.testng.annotations.Test;

/**
 * 64位虚拟机->64位（8字节）markword
 * 32位虚拟机->32位（4字节）markword
 * 4字节的类型指针
 * [4字节的数组长度]
 * 实例数据
 * 补齐为8的倍数。
 *
 * 采用的是little-endian，越往后的字节越小位。
 * 所以表示锁的最后两位所在的位置，是第一个字节的最后两位。
 */
public class JOLTest {


    class AA {

    }

    @Test
    public void test1() throws InterruptedException {
        //尚未开启偏向锁，所以对象头中的状态是001(无锁）。
        AA aa = new AA();
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
        /**
         * # WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
         * com.marstalk.jdk8.jvm.javaobjectlayout.JOLTest$AA object internals:
         *  OFFSET  SIZE                                             TYPE DESCRIPTION                               VALUE
         *       0     4                                                  (object header)                           01 00 00 00 (00000【0】【01】 00000000 00000000 00000000) (1)
         *       4     4                                                  (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                                  (object header)                           a1 1b 01 f8 (10100001 00011011 00000001 11111000) (-134145119)
         *      12     4   com.marstalk.jdk8.jvm.javaobjectlayout.JOLTest AA.this$0                                 (object)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes totalj
         */
        synchronized (aa) {
            //这里从对象头里可以看到，启用的是轻量级锁00，因为JVM默认延迟4秒开启偏量锁设置。
            System.out.println(ClassLayout.parseInstance(aa).toPrintable());
            /**
             *  OFFSET  SIZE                                             TYPE DESCRIPTION                               VALUE
             *       0     4                                                  (object header)                           f0 28 6b 06 (111100【00】 00101000 01101011 00000110) (107686128)
             *       4     4                                                  (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
             *       8     4                                                  (object header)                           a1 1b 01 f8 (10100001 00011011 00000001 11111000) (-134145119)
             *      12     4   com.marstalk.jdk8.jvm.javaobjectlayout.JOLTest AA.this$0                                 (object)
             * Instance size: 16 bytes
             * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
             */
        }

        //对象头回到了无锁的状态。 001
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
        /**
         *  OFFSET  SIZE                                             TYPE DESCRIPTION                               VALUE
         *       0     4                                                  (object header)                           01 00 00 00 (00000【0】【01】 00000000 00000000 00000000) (1)
         *       4     4                                                  (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                                  (object header)                           a1 1b 01 f8 (10100001 00011011 00000001 11111000) (-134145119)
         *      12     4   com.marstalk.jdk8.jvm.javaobjectlayout.JOLTest AA.this$0                                 (object)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */




        //睡眠6秒，保证偏量锁已经启用。
        Thread.sleep(6*1000);
        AA aaa = new AA();
        //markword: 001，线程ID为0， 也叫做匿名偏向锁。
        System.out.println(ClassLayout.parseInstance(aaa).toPrintable());
        /**
         * com.marstalk.jdk8.jvm.javaobjectlayout.JOLTest$AA object internals:
         *  OFFSET  SIZE                                             TYPE DESCRIPTION                               VALUE
         *       0     4                                                  (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
         *       4     4                                                  (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                                                  (object header)                           a1 1b 01 f8 (10100001 00011011 00000001 11111000) (-134145119)
         *      12     4   com.marstalk.jdk8.jvm.javaobjectlayout.JOLTest AA.this$0                                 (object)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        synchronized (aaa){
            //markword: 001，线程ID为当前线程ID。
            System.out.println(ClassLayout.parseInstance(aaa).toPrintable());
        }

    }
}
