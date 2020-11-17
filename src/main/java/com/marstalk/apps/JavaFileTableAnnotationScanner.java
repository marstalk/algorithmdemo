package com.marstalk.apps;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 根据根目录，遍历所有的Java文件，遍历每一行。 找到使用了@Table注解的，并且把相关信息解析出来。
 *
 * @author shanzhonglaosou
 */
public class JavaFileTableAnnotationScanner {

    private static Set<String> resultSet = new HashSet<>();
    private static int count = 0;

    public static void main(String[] args) {
        System.out.println("qgen-designer:");
        scan(new File("D:\\synnex\\ittool\\qgen\\qgen-designer"));

        System.out.println();
        System.out.println("qgen-screen:");
        scan(new File("D:\\qgen10\\qgen-screen"));
        System.out.println("Count(qgen-screen) + Count(qgen-designer) is " + count);

        System.out.println();
        System.out.println("Merge result:" + resultSet.size());
        resultSet.stream().sorted().forEach(System.out::println);

        System.out.println("exclude database QGEN: " + resultSet.stream().filter(s -> !(s.startsWith("QGEN") || s.startsWith("qgen"))).count());
        resultSet.stream().filter(s -> !(s.startsWith("QGEN") || s.startsWith("qgen"))).sorted().forEach(System.out::println);
    }

    private static void scan(File file) {
        if (file.isDirectory()) {
            //directory
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files) {
                scan(f);
            }
        }
        // java file, handle every line
        if (file.getName().endsWith(".java")) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                /**
                 * example: @Table(name = "qgen_validation_message",  catalog = "QGEN")
                 */
                String result = bufferedReader.lines().filter(line -> line.contains("@Table"))
                        //@Table(name = "qgen_validation_message",  catalog = "QGEN")
                        //.peek(s -> System.out.println(s))
                        .map(s -> s.replace("@Table(", "").replace(")", ""))
                        //name = "qgen_validation_message",  catalog = "QGEN"
                        //.peek(s -> System.out.println(s))
                        .flatMap((Function<String, Stream<String>>) s -> Arrays.stream(s.split(",")))
                        .flatMap((Function<String, Stream<String>>) o -> Arrays.stream(o.split("=")))
                        .map(String::trim)
                        .filter(s -> !("name".equalsIgnoreCase(s) || "catalog".equalsIgnoreCase(s)))
                        .map(s -> s.replace("\"", ""))
                        .sorted()
                        //.peek(s -> System.out.println(s))
                        .collect(Collectors.joining("."));
                if (!StringUtils.isEmpty(result)) {
                    System.out.println(result);
                    resultSet.add(result);
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
