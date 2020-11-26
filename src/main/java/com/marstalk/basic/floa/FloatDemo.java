package com.marstalk.basic.floa;

/**
 * Java能够正确的存储浮点型数据，但是无法正确的处理浮点数据的积？
 * @author shanzhonglaosou
 */
public class FloatDemo {
    static float f = 1.3F;

    public static void main(String[] args) {
        //1.6899998
        System.out.println(f * f);

        //0.4333333
        System.out.println(f / 3);

        //2.6
        System.out.println(f * 2);
    }
}
