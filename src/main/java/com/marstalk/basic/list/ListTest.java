package com.marstalk.basic.list;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {

    @Test
    public void deleteListElement(){
        List<String> c = new ArrayList<>();
        c.add("a");
        c.add("b");
        c.add("c");
        for (int i = 0; i < c.size(); i++) {
            c.remove(i);
            System.out.println(c.get(i));
        }
    }

    @Test
    public void deleteListElement2(){
        List<String> c = new ArrayList<>();
        c.add("a");
        c.add("b");
        c.add("c");
        Iterator<String> iterator = c.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
    }

}
