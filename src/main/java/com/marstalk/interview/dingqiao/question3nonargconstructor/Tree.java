package com.marstalk.interview.dingqiao.question3nonargconstructor;


public class Tree extends Plant {

    /**
     * 【考点】
     * 默认情况下，Tree的无参构造器一定要调用父类的无参构造器。
     * 再这个例子中，Plant没有无参构造器。
     * 那么Tree默认无参构造器就无法正常工作了。
     * 所以自类一定要手动提供构造器（无参或者有参），并且在构造器里调用特定父类的构造器。即调用super(xx)
     *
     * @param name
     */
    public Tree(String name) {
        super(name);
    }

//    public Tree(){
//        super("Tom");
//    }

    public void growFruit() {

    }

    public void dropLeaves() {

    }
}
