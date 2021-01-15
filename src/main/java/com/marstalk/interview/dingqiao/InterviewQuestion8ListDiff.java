package com.marstalk.interview.dingqiao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterviewQuestion8ListDiff {
    public static void main(String[] args) {
        List arr1 = new ArrayList<>();
        List<Integer> arr2 = Arrays.asList(1, 2, 3);
        System.out.println(arr1.getClass().equals(arr2.getClass()));
        arr2.add(4);
    }
}
