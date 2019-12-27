package com.marstalk.concurrent.jmm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 这个例子，表示，JVM为了优化代码的运行速度，会重新排序我们的指令。
 * 因为x，y都没有使用volatile修饰，即没有关闭重排序，所有结果中，会出现x=1，y=1的情况。
 * <p>
 * volatile保证有序性。
 *
 * @author Mars
 * Created on 12/3/2019
 */
public class VolatileSerialTest {

    static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        HashSet<Object> resultSet = new HashSet<>();
        HashMap<Object, Object> resultMap = new HashMap<>();

        //一百万次循环。
        for (int i = 0; i < 1000000; i++) {
            x = 0;
            y = 0;
            resultMap.clear();
            Thread one = new Thread(() -> {
                int a = y;
                x = 1;
                resultMap.put("a", a);
            });

            Thread other = new Thread(() -> {
                int b = x;
                y = 1;
                resultMap.put("b", b);
            });

            one.start();
            other.start();
            one.join();
            other.join();
            resultSet.add("a=" + resultMap.get("a") + ", " + "b=" + resultMap.get("b"));
            System.out.println(resultSet);
        }

    }

}
