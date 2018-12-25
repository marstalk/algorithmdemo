package com.marstalk.jdk8.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Mars
 * Created on 2018/8/12
 */
public class map {
    public static void main(String[] args) throws IOException {


//        List<Person> personList = new ArrayList<>();
//        List<Person> maleList = new ArrayList<>();
//        for (Person person : personList) {
//            if (person.getGender() == Person.MALE && person.getAge() > 18) {
//                maleList.add(person);
//            }
//        }
//        Collections.sort(maleList, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getAge() - o2.getAge();
//            }
//        });
//        List<Integer> ids = new ArrayList<>();
//        for (Person t : maleList) {
//            ids.add(t.getAge());
//        }
//
//        personList.stream()
//                .filter(person -> person.getAge() > 18)
//                .filter(person -> person.getGender() == Person.MALE)
//                .sorted(Comparator.comparing(Person::getAge))
//                .map(Person::getAge)
//                .collect(Collectors.toList());

        List<String> strings = Arrays.asList("welcome", "to", "synnex");
        List<String> collect = strings.stream()
                .map(String::toUpperCase)
                .peek(System.out::println)
                .collect(Collectors.toList());


//        Stream<Integer> stream = Arrays.asList(1, 2, 3).stream();
//        Double collect1 = stream.map(n -> n * n).collect(Collectors.averagingInt(Integer::intValue));
//        List<Integer> collect2 = stream.map(n -> n * n).collect(Collectors.toList());
//
//        System.out.println(collect1);
//        System.out.println(collect2);


        //even number
//        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7};
//        Integer[] integers = Stream.of(numbers).filter(n -> n % 2 != 0).toArray(Integer[]::new);
//        Arrays.stream(integers).forEach(n -> System.out.println(n));


        //
        long begin = System.currentTimeMillis();
        List<String> testList = Files.readAllLines(Paths.get("E:\\algorithmdemo\\test.txt"));
        List<String> collect1 = testList.stream().flatMap(m -> Stream.of(m.split(" "))).filter(m -> m.length() > 0).collect(Collectors.toList());
        long count = testList.stream().flatMap(m -> Stream.of(m.split(" "))).filter(m -> m.length() > 0).count();
        System.out.println(collect1);
        System.out.println(count);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);


//        begin = System.currentTimeMillis();
//        List<String> testList2 = Files.readAllLines(Paths.get("E:\\algorithmdemo\\test.txt"));
//        List<String> strngList = testList2.parallelStream().flatMap(m -> Stream.of(m.split(" "))).filter(m -> m.length() > 0).collect(Collectors.toList());
//        count = testList2.parallelStream().flatMap(m -> Stream.of(m.split(" "))).filter(m -> m.length() > 0).count();
//        System.out.println(strngList);
//        System.out.println(count);
//        end = System.currentTimeMillis();
//        System.out.println(end - begin);


//        List<String> strings1 = Arrays.asList("A", "B", "C", "D");
////        String reduce = strings1.stream().reduce("", String::concat);
////        System.out.println(reduce);

        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);


        Double reduce = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(0.0, Double::sum);
        System.out.println(reduce);

        String s = Stream.of("A", "a", "C", "c", "b", "d", "D").filter(m -> m.compareTo("Z") > 0).sorted().reduce(String::concat).orElse(null);
        System.out.println(s);

        String s1 = Stream.of("r", "A", "a", "C", "c", "b", "d", "D").filter(m -> m.compareTo("Z") == 1).skip(1).sorted().peek(System.out::println).reduce(String::concat).orElse(null);
        System.out.println("s1:" + s1);

        testLimitAndSkip();

        //distinct
//        List<String> strings1 = Files.readAllLines(Paths.get("E:\\\\algorithmdemo\\\\test.txt"));
//        strings1.stream().flatMap(m -> Stream.of(m.split(" "))).peek(m -> m.toLowerCase()).distinct().sorted().forEach(System.out::println);


        //generate stream
        Random random = new Random(0);
        Supplier<Integer> randomSupplier = random::nextInt;
        Stream.generate(randomSupplier).limit(10).forEach(System.out::println);

        IntStream.generate(() -> (int) System.nanoTime() % 100).limit(10).forEach(System.out::println);

        Stream.generate(new PersonSupplier()).limit(10).sorted().forEach(System.out::println);

        //iterate
        Stream.iterate(1, n -> n * 2).limit(10).forEach(System.out::println);

        //groupby 1
//        Map<Integer, List<Person>> collect1 = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.groupingBy(person -> person.getAge()));
//        for (Map.Entry entry : collect1.entrySet()) {
//            System.out.println(entry.getKey() + ": " + ((List) entry.getValue()).size());
//        }

        //groupby 2
        Map<Boolean, List<Person>> collect2 = Stream.generate(new PersonSupplier()).limit(100).collect(Collectors.partitioningBy(n -> n.age > 50));
        for (Map.Entry entry : collect2.entrySet()) {
            System.out.println(entry.getKey() + ": " + ((List) entry.getValue()).size());
        }

    }

    public static void testLimitAndSkip() {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, 2, "name" + i);
            persons.add(person);
        }
        List<String> personList2 = persons.stream().
                map(person -> {
                    System.out.println(person.toString());
                    return person.getName();
                }).limit(10).skip(3).sorted().collect(Collectors.toList());
//        List<String> personList2 = persons.stream().
//                map(Person::getName).sorted().limit(10).skip(3).sorted().collect(Collectors.toList());
        System.out.println(personList2);
    }


}

class Person implements Comparable<Person> {
    public static int MALE = 1;
    public static int FEMAIL = 2;
    public int no;
    public int age;
    private String name;
    private int gender;

    public Person(int no, int age, String name) {
        this.no = no;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(Person o) {
        return this.no - o.no;
    }

    @Override
    public String toString() {
        return "Person{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

class PersonSupplier implements Supplier<Person> {
    Random random = new Random(9);

    @Override
    public Person get() {
        return new Person(random.nextInt(10), random.nextInt(90), String.valueOf(random.nextInt(10)));
    }
}

class Transaction {

}
