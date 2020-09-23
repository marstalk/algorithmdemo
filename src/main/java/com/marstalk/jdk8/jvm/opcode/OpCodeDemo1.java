package com.marstalk.jdk8.jvm.opcode;


import org.junit.jupiter.api.Test;

public class OpCodeDemo1 {
    public static void main(String[] args) {
        String name = "louisl";
        String a = greeting(name);
    }

    private static String greeting(String name) {
        System.out.println("Hello, " + name);
        return name + " hello;";
    }

    public int i(){
        /**
         * 0 iconst_4
         *  1 istore_1
         *  2 getstatic #4 <java/lang/System.out>
         *  5 iload_1
         *  6 iinc 1 by 1
         *  9 invokevirtual #12 <java/io/PrintStream.println>
         * 12 iload_1
         * 13 ireturn
         */
        int i = 4;
        System.out.println(i++);
        return i;
    }

    public void ii(){
        int i = 0;
        System.out.println(++i);//顺序不一样。
        /**
         *   0 iconst_0
         *  1 istore_1
         *  2 getstatic #4 <java/lang/System.out>
         *  5 iinc 1 by 1
         *  8 iload_1
         *  9 invokevirtual #12 <java/io/PrintStream.println>
         * 12 return
         */
    }

    public void iii(){
        int i = 0;
        i = i - 3;
        System.out.println(i--);
    }

    @Test
    public void j(){
        int i = 0;
        System.out.println(i++ + ++i + i-- - --i);
    }

    /**
     *  0 aload_1
     *  1 ifnonnull 12 (+11)
     *  4 getstatic #4 <java/lang/System.out>
     *  7 ldc #13 <1>
     *  9 invokevirtual #10 <java/io/PrintStream.println>
     * 12 aconst_null
     * 13 aload_1
     * 14 if_acmpne 25 (+11)
     * 17 getstatic #4 <java/lang/System.out>
     * 20 ldc #14 <22>
     * 22 invokevirtual #10 <java/io/PrintStream.println>
     * 25 return
     * @param str
     */
    public void equal(String str){
        if (str == null) {
            System.out.println("1");
        }
        if (null == str) {
            System.out.println("22");
        }
    }


}
