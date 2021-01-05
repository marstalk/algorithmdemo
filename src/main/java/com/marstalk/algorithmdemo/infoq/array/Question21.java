package com.marstalk.algorithmdemo.infoq.array;

/**
 * You are given arrival and departure time of trains reaching to a particular station. <br>
 * You need to find minimum number of platforms required to accommodate the trains at any point of time.
 */
public class Question21 {
    public static void main(String[] args) {
        // arr[] = {1:00, 1:40, 1:50, 2:00, 2:15, 4:00}
        // dep[] = {1:10, 3:00, 2:20, 2:30, 3:15, 6:00}

        int arr[] = {100, 140, 150, 200, 215, 400};
        int dep[] = {110, 300, 210, 230, 315, 600};
        System.out.println("Minimum platforms needed:" + findPlatformsRequiredForStation(arr, dep));
    }

    /**
     * 这个有点儿想漏桶算法，<br>
     * 流量按照实时速率（不断变化）进入漏桶，如果漏桶满了则抛弃请求。<br>
     * 漏桶按照一定速率出漏桶，<br>
     * 求漏桶至少多大，才能保证所有的流量请求都不会被抛弃。<br>
     *
     * @param arr
     * @param dep
     * @return
     */
    private static int findPlatformsRequiredForStation(int[] arr, int[] dep) {
        int arrIndex = 0, depIndex = 0, count = 0, maxCount = 0;

        //主要是关注进站的火车。如果还有进展的火车，则还可能产生更大的站台数（maxCount）
        while (arrIndex < arr.length) {
            if (arr[arrIndex] < dep[depIndex]) {
                //火车进站，进站指针向前一步
                // 则当前需要count++的站台
                arrIndex++;
                count++;
                if (count > maxCount) {
                    //如果当前需要的站台count大于历史最大数，那么则更新历史最大数。
                    maxCount = count;
                }
            } else if (arr[arrIndex] < dep[depIndex]) {
                //火车离站，指针向前一步
                depIndex++;
                //所需站台数减一
                count--;
            } else {
                arrIndex++;
                depIndex++;
            }

        }
        return maxCount;
    }
}

























