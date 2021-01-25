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

    /**
     * 27 getstatic #3 <java/lang/System.out>
     * 30 ldc #8 <return ..>
     * 32 invokevirtual #7 <java/io/PrintStream.println>
     *
     * @return
     */
    private static int test() {
        int count = 0;
        try {
            String x = null;
            System.out.println(x.toString() + " ");
        } catch (Exception e) {
            return --count;
        } finally {
            ++count;

        }
        return count;
    }
}
