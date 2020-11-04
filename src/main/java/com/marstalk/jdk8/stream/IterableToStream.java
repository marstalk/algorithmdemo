package com.marstalk.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 如何正确地将Iterable转为Stream
 *
 * @author shanzhonglaosou
 */
public class IterableToStream {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("99");

        //需要把list<String>转成这种格式: 'element1', 'element2', 'element3'
        toSqlValue(list);
        toSqlValue2(list);
    }

    /**
     * 不符合要求
     *
     * @param iterable
     * @return '[a, 99]'
     */
    static String toSqlValue(Iterable<String> iterable) {
        String collect = Stream.of(iterable).map(s -> "'" + s + "'").collect(Collectors.joining(","));
        System.out.println(collect);
        return collect;
    }

    /**
     * 这种才符合
     *
     * @param iterable
     * @return 'a','99'
     */
    static String toSqlValue2(Iterable<String> iterable) {
        String collect1 = StreamSupport.stream(iterable.spliterator(), false).map(s -> "'" + s + "'").collect(Collectors.joining(","));
        System.out.println(collect1);
        return collect1;
    }

}
