package com.marstalk.interview.dingqiao;

public class InterviewQuestion6Finally {
    public static void main(String[] args) {
        try {
            int result = test();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("exception ");
        }
    }

    private static int test() {
        // 0 iconst_0
        // 1 istore_0
        int count = 0;
        try {
            //  2 aconst_null
            //  3 astore_1
            String x = null;

            //System.out.println(x.toString() + " ");
        } catch (Exception e) {
            //System.out.println("catch.. ");
            return --count;
        } finally {
            //finally一定会执行 except from System.exit and infinite loops
            // 4 iinc 0 by 1
            // goto 27 (+20)
            ++count;
            //System.out.println("finally.. count=" + count);
        }

        //27 getstatic #3 <java/lang/System.out>
        //30 ldc #8 <return ..>
        //32 invokevirtual #7 <java/io/PrintStream.println>
        System.out.println("return ..");
        return count;
    }
}
