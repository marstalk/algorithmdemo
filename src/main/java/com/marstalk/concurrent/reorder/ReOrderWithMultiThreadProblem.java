package com.marstalk.concurrent.reorder;

public class ReOrderWithMultiThreadProblem {
    private int a;
    private boolean flag;

    public void write() {
        /**
         * 这两行代码可能发生重排序，变成：
         * flag = true;
         * a = 1;
         * 即它两的执行顺序，肯能会掉换。
         * 在单线程看来，这两种执行顺序都是一样的。
         */
        a = 1;
        flag = true;
    }

    public int read() {
        /**
         * 这几行代码也可能发生重排序：
         * int tem = a*2;
         * if(flag){
         *     return tem;
         * }
         * return 0;
         */
        if (flag) {
            return a * 2;
        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        ReOrderWithMultiThreadProblem reOrderExample = new ReOrderWithMultiThreadProblem();
        Thread t1 = new Thread(() -> {
            reOrderExample.write();
        });
        Thread t2 = new Thread(() -> {
            int result = reOrderExample.read();
            System.out.println(result);
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
