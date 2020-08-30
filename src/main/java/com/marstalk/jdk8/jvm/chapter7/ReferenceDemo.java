package com.marstalk.jdk8.jvm.chapter7;

/**
 * 主动引用（进行初始化Initialization）有且只有以下五种：
 * 1）new, getstatic, putstatic, invokestatic
 * 2) reflect反射调用的时候
 * 3）初始化一个类时，其父类没有尚未进行初始化。
 * 4）虚拟机启动时候，需要制定一个执行的主类（包含main方法的），虚拟机会初始化这个类
 * 5）java.lang.invoke.MethodHandle，
 *
 * 以上五种情况称为主动引用。
 * Demo1
 */
public class ReferenceDemo {
    static{
        System.out.println("Main class init for 4）虚拟机启动时候，需要制定一个执行的主类（包含main方法的），虚拟机会初始化这个类");
    }

    /**
     * 根据第四点，虚拟机会先加载Main方法所在的类：
     *      Main class init for 4）虚拟机启动时候，需要制定一个执行的主类（包含main方法的），虚拟机会初始化这个类
     * 根据第三点：父类尚未加载，则加载父类：
     *      father init
     * 根据第一点：new指令
     *      son init
     * 根据第一个点：getstatic指令，但是因为父类已经加载过了，不需要再加载了。
     *      init daughter
     *
     *  0 new #2 <com/marstalk/jdk8/jvm/chapter7/Son>
     *  3 dup
     *  4 invokespecial #3 <com/marstalk/jdk8/jvm/chapter7/Son.<init>>
     *  7 astore_1
     *  8 getstatic #4 <java/lang/System.out>
     * 11 getstatic #5 <com/marstalk/jdk8/jvm/chapter7/Daughter.name>
     * 14 invokevirtual #6 <java/io/PrintStream.println>
     * 17 return
     * @param args
     */
    public static void main(String[] args) {
        //new
        //dup
        //invokespecial
        //astore
        Son son = new Son();

        //getstatic
        System.out.println(Daughter.name);
    }

}
