package com.marstalk.jdk8.jvm.opcode;

public class OpCodeDemo2 {

    public static void main(String[] args) {
        //iconst_1 常量1压入栈顶
        //invokestatic #2 <java/lang/Integer.valueOf> 栈顶出栈，参与常量池#2类调用valueOf静态方法，返回值reference压入栈顶
        //astore_1 栈顶reference出栈，放到变量槽1中。
        Integer integer = 1;

        //new #3 <com/marstalk/jdk8/jvm/opcode/Int> 创建对象，并把reference压入栈顶
        //dup 复制栈顶元素并压入栈顶
        //aload_1 将变量槽1压入栈顶
        //invokespecial #4 <com/marstalk/jdk8/jvm/opcode/Int.<init>> 栈上的两个元素出栈，参与init方法
        //astore_2 栈顶reference出栈放入变量槽2中
        Int i1 = new Int(integer);

        //new #3 <com/marstalk/jdk8/jvm/opcode/Int> 创建对象，并发reference压入栈顶
        //dup 复制栈顶元素并压入栈顶
        //aload_2 变量槽2压入栈顶
        //invokespecial #5 <com/marstalk/jdk8/jvm/opcode/Int.<init>> 栈上两个元素参与init方法
        //astore_3 栈顶元素出栈并放入变量槽3中
        Int i2 = new Int(i1);

        //iconst_2 常量2压入栈顶
        //invokestatic #2 <java/lang/Integer.valueOf> 栈顶出栈，参与常量池#2类调用valueOf静态方法，返回值reference压入栈顶
        //astore_1 将栈顶的元素弹出，并放入变量槽1中。
        // 【重点】integer=2，会构造一个新的Integer对象，并放入到变量槽中（即复制给interger），并没有改变i1，i2中的值
        // 【重点】 所以接下来的System.out.println(i1.integer);输出是1
        integer = 2;

        System.out.println(i1.integer);

        //aload_2 将变量槽2中的reference中压入栈顶
        //iconst_4 将常量4压入栈顶
        //invokestatic #2 <java/lang/Integer.valueOf> 栈顶出栈，并构造新的Integer对象，得到的reference压入栈顶。
        //putfield #7 <com/marstalk/jdk8/jvm/opcode/Int.integer> 此刻操作数栈有两个reference，一个是指向i1的，一个是指向新的Integer（4）的，
        // putfield之后，i1的属性执行新的Integer（4）
        i1.integer = 4;

        //所以输出的是4
        System.out.println(i2.i.integer);
    }

}

class Int {

    Integer integer;
    Int i;

    Int(Int i) {
        this.i = i;
    }

    Int(Integer integer) {
        this.integer = integer;
    }

}
