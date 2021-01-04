package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 18：在已旋转和排序的数组中搜索元素。
 * 什么是旋转数组？
 * 在一个排序数组中，将数组分为两个部分[A, B]，然后把这两个部分颠倒顺序变成[B, A]，其中A和B分别都是顺序的。
 * 【只要给定的数组是排好序的，那么算法复杂度一般都要控制在logN以下。
 * 但是题目中的数组不是严格意义的单调性。所以这里需要分两步：
 * 1）使用二分找到分段点（如左侧最大值或者右侧最小值），可以判断目标元素在左侧还是右侧分段。
 * 2）找到分段后，再使用二分查找元素。
 * 时间复杂度是2*logN，约等于logN
 */
public class Question18 {
    public static void main(String[] args) {
        int arr[]={16,19,21,25,66,3,5,8,10, 11};
        System.out.println("Index of element 5 : "+findElementRotatedSortedArray(arr,0,arr.length-1,5));
    }
    
    private static int findElementRotatedSortedArray(int[] arr, int l, int r, int target) {
        //第一步，找到断点所在位置，并且比较所在位置的值与目标值如何。
        int ll = l;
        int rr = r;
        while (l < r) {
            int m = l + (r -l)/2;
            if (arr[m] > arr[l]) {
                //m点在左侧。
                l=m;
            }{
                //m点在右侧。
                r=m;
            }
        }
        System.out.println(l);
        
        //l就是左侧最大的值的位置，r就是
        if (target == arr[ll]) {
            return ll;
        }else if (target > arr[ll]) {
            rr = l;
        }else{
            ll = l;
        }
    
        while (rr - ll > 1) {
            int mid = ll + (rr - ll)/2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                rr = mid;
            }else{
                ll = mid;
            }
        }
        
        return -1;
    }
}
