package com.marstalk.jdk8.parallelstream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mars
 * Created on 2018/8/27
 */
public class ParallelStreamDemo {
    public static void main(String[] args) throws IOException {
        //统计单词个数
        long begin = System.currentTimeMillis();
        List<String> strings = Files.readAllLines(Paths.get("E:\\500M.txt"));
        long count = strings.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).count();
        System.out.println("stream count:" + (System.currentTimeMillis() - begin) / 1000 + " s");

        begin = System.currentTimeMillis();
        List<String> strings2 = Files.readAllLines(Paths.get("E:\\500M.txt"));
        long count2 = strings2.parallelStream().map(s -> s.split(" ")).flatMap(Arrays::stream).count();
        System.out.println("parallelStream count:" + (System.currentTimeMillis() - begin) / 1000 + " s");
    }
}
