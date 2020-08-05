package com.marstalk.basic.innerclass;

public class InnerClassDemo {
    public void hi() throws IllegalAccessException, InstantiationException {
        //SomeInnerClass.MyInnerClass myInnerClass = new SomeInnerClass.MyInnerClass();
        SomeInnerClass someInnerClass = new SomeInnerClass();
        Class<SomeInnerClass.MyInnerClass> myInnerClassClass = SomeInnerClass.MyInnerClass.class;
        SomeInnerClass.MyInnerClass myInnerClass = myInnerClassClass.newInstance();
        myInnerClass.setAge(3);

        SomeInnerClass.MyStaticInnerClass myStaticInnerClass = new SomeInnerClass.MyStaticInnerClass();
        myStaticInnerClass.getName();
        int i = 1;
        int ii = 2;
        int iii = i + ii;
    }
}
