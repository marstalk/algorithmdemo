package com.marstalk.concurrent.d431_javapsynchronize;

/**
 * 通过Javap -p Synchronized.class 可以看到class文件的信息。
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class) {//monitorenter
            System.out.println(2);
        }//monitorexit
        m();
    }

    public static synchronized void m() {
    }//flags: ACC_STATIC ACC_PUBLIC [ACC_SYNCHRONIZED]
}
/**
 * Classfile /E:/algorithmdemo/target/classes/com/marstalk/concurrent/d4_javapsynchronize/Synchronized.class
 * Last modified 2019-12-15; size 795 bytes
 * MD5 checksum 58568080e7aaaf05998c7bae667f9f6d
 * Compiled from "Synchronized.java"
 * public class com.marstalk.concurrent.d4_javapsynchronize.Synchronized
 * minor version: 0
 * major version: 52
 * flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 * #1 = Methodref          #6.#26         // java/lang/Object."<init>":()V
 * #2 = Class              #27            // com/marstalk/concurrent/d4_javapsynchronize/Synchronized
 * #3 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
 * #4 = Methodref          #30.#31        // java/io/PrintStream.println:(I)V
 * #5 = Methodref          #2.#32         // com/marstalk/concurrent/d4_javapsynchronize/Synchronized.m:()V
 * #6 = Class              #33            // java/lang/Object
 * #7 = Utf8               <init>
 * #8 = Utf8               ()V
 * #9 = Utf8               Code
 * #10 = Utf8               LineNumberTable
 * #11 = Utf8               LocalVariableTable
 * #12 = Utf8               this
 * #13 = Utf8               Lcom/marstalk/concurrent/d4_javapsynchronize/Synchronized;
 * #14 = Utf8               main
 * #15 = Utf8               ([Ljava/lang/String;)V
 * #16 = Utf8               args
 * #17 = Utf8               [Ljava/lang/String;
 * #18 = Utf8               StackMapTable
 * #19 = Class              #17            // "[Ljava/lang/String;"
 * #20 = Class              #33            // java/lang/Object
 * #21 = Class              #34            // java/lang/Throwable
 * #22 = Utf8               MethodParameters
 * #23 = Utf8               m
 * #24 = Utf8               SourceFile
 * #25 = Utf8               Synchronized.java
 * #26 = NameAndType        #7:#8          // "<init>":()V
 * #27 = Utf8               com/marstalk/concurrent/d4_javapsynchronize/Synchronized
 * #28 = Class              #35            // java/lang/System
 * #29 = NameAndType        #36:#37        // out:Ljava/io/PrintStream;
 * #30 = Class              #38            // java/io/PrintStream
 * #31 = NameAndType        #39:#40        // println:(I)V
 * #32 = NameAndType        #23:#8         // m:()V
 * #33 = Utf8               java/lang/Object
 * #34 = Utf8               java/lang/Throwable
 * #35 = Utf8               java/lang/System
 * #36 = Utf8               out
 * #37 = Utf8               Ljava/io/PrintStream;
 * #38 = Utf8               java/io/PrintStream
 * #39 = Utf8               println
 * #40 = Utf8               (I)V
 * {
 * public com.marstalk.concurrent.d4_javapsynchronize.Synchronized();
 * descriptor: ()V
 * flags: ACC_PUBLIC
 * Code:
 * stack=1, locals=1, args_size=1
 * 0: aload_0
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: return
 * LineNumberTable:
 * line 6: 0
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0       5     0  this   Lcom/marstalk/concurrent/d4_javapsynchronize/Synchronized;
 * <p>
 * public static void main(java.lang.String[]);
 * descriptor: ([Ljava/lang/String;)V
 * flags: ACC_PUBLIC, ACC_STATIC
 * Code:
 * stack=2, locals=3, args_size=1
 * 0: ldc           #2                  // class com/marstalk/concurrent/d4_javapsynchronize/Synchronized
 * 2: dup
 * 3: astore_1
 * 4: monitorenter
 * 5: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 * 8: iconst_2
 * 9: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 * 12: aload_1
 * 13: monitorexit
 * 14: goto          22
 * 17: astore_2
 * 18: aload_1
 * 19: monitorexit
 * 20: aload_2
 * 21: athrow
 * 22: invokestatic  #5                  // Method m:()V
 * 25: return
 * Exception table:
 * from    to  target type
 * 5    14    17   any
 * 17    20    17   any
 * LineNumberTable:
 * line 8: 0
 * line 9: 5
 * line 10: 12
 * line 11: 22
 * line 12: 25
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0      26     0  args   [Ljava/lang/String;
 * StackMapTable: number_of_entries = 2
 * frame_type = 255 // full_frame
 * offset_delta=17
 * locals=[class "[Ljava/lang/String;",
 * <p>
 * class java/lang/Object]
 * stack=[
 * <p>
 * class java/lang/Throwable]
 * frame_type=250  //chop
 * offset_delta=4
 * MethodParameters:
 * Name Flags
 * args
 * <p>
 * public static synchronized void m();
 * descriptor:()V
 * flags:ACC_PUBLIC,ACC_STATIC,ACC_SYNCHRONIZED
 * Code:
 * stack=0,locals=0,args_size=0
 * 0:return
 * LineNumberTable:
 * line 14:0
 * }
 * SourceFile:"Synchronized.java"
 */

