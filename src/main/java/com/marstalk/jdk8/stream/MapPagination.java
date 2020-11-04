package com.marstalk.jdk8.stream;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapPagination {

    public static void main(String[] args) {
        Map<Integer, Salary> maps = new HashMap<>();
        maps.put(10001, new Salary(10001, 5555F, "it"));
        maps.put(10002, new Salary(10002, 123F, "hr"));
        maps.put(10003, new Salary(10003, 3432F, "ittools"));
        maps.put(10004, new Salary(10004, 444.3F, "bpms"));
        maps.put(10005, new Salary(10005, 888.0F, "cad"));
        maps.put(10007, new Salary(10006, 3453.6F, "prm"));

        String nameKey = "t";
        int pageSize = 2;
        int pageNo = 1;
        String sort = "id";

        int skip = (pageNo - 1) * pageSize;
        maps.values().stream()
                .filter(salary -> salary.name.contains(nameKey))
                .sorted(Comparator.comparingInt(o -> o.id))
                .skip(skip)
                .limit(pageSize)
                .forEach(salary -> System.out.println(salary));

    }

    static class Salary{
        int id;
        float salary;
        String name;

        public Salary(int id, float salary, String name) {
            this.id = id;
            this.salary = salary;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Salary{" +
                    "id=" + id +
                    ", salary=" + salary +
                    ", name='" + name + '\'' +
                    '}';
        }

    }
}
