package com.marstalk.algorithmdemo.infoq.array;

/**
 * 分离奇偶数
 */
public class Question27 {
    public static void main(String[] args) {
        int arr[] = {12, 17, 70, 15, 22, 65, 21, 90};
        System.out.println("Original Array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        separateEvenOddNumbers(arr);
        System.out.println("nArray after separating even and odd numbers : ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 这个问题的解决办法，跟分离0，1是一样的。
     * 分离后，左边是偶数even，右边是奇数odd
     * @param arr
     * @return
     */
    private static int[] separateEvenOddNumbers(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            //如果左元素是奇数，且右元素是偶数，则调换为止，然后指针都先前一步。
            boolean leftIsOdd = arr[l] % 2 == 1;
            boolean rightIsEven = arr[r] % 2 == 0;

            if (leftIsOdd && rightIsEven) {
                //先交换
                int tem = arr[l];
                arr[l] = arr[r];
                arr[r] = tem;
                //再移动
                l++;
                r--;
            }else{
                if (!leftIsOdd) {
                    l++;
                }
                if (!rightIsEven) {
                    r--;
                }
            }
        }
        return arr;
    }
}
