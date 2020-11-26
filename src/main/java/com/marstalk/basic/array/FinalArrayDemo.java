package com.marstalk.basic.array;

/**
 * final修饰的变量只能被被赋值一次，即
 * 基本类型的值只能被赋值一次。
 * 引用类型的指针只能被赋值一次，但是指向的对象可以被多次修改。
 * 比如final修饰的数组，变量保存的是引用地址，即引用地址只能被赋值一次，
 * 但是数组的元素值能够被多次修改。
 * @author shanzhonglaosou
 */
public class FinalArrayDemo {
    public static void main(String[] args) {
        final char[] chars;
        chars = new char[5];

        //chars是final修饰了，只能赋值一次。
        //chars = new char[4];

        //但是它的元素是可以被多次修改的。
        chars[0] = '1';
        chars[2] = 'a';
        chars[0] = 'b';


        for (char c : chars) {
            System.out.println(c);
        }
    }
}
