package com.marstalk.jdk7newfeature;

import java.math.BigDecimal;
import java.util.concurrent.RecursiveTask;

/**
 * 计算1+...+10000的值.
 */
public class ForkAndJoinDemoRecursiveTask {
    public static void main(String[] args) {
        //solution 1
        BigDecimal l = new BigDecimal(0);
        for (long i = 0; i < 9999999999L; i++) {
            l = l.add(new BigDecimal(i));
        }
        System.out.println(l);


    }
}


class MyRecursiveTask extends RecursiveTask<Long> {
    private BigDecimal bigDecimal;

    @Override
    protected Long compute() {
        if (bigDecimal.min(new BigDecimal(5000 * 000)).intValue() > 0) {

        } else {
            return 0L;
        }
        return 0L;
    }
}