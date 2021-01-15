package com.marstalk.interview.dingqiao;

import java.util.Set;
import java.util.TreeSet;

/**
 * 排序的set：TreeSet
 */
public class InterviewQuestion9set {
    public static void main(String[] args) {
        //List set = new SortedList<>();
//        Set set = new LinkedHashSet();
        Set set = new TreeSet();
        set.add(new Integer(2));
        set.add(new Integer(1));
        set.add(new Integer(3));
        System.out.println(set);
    }
}
