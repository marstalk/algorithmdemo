package com.marstalk.jdk8.jvm.oom;

import org.testng.annotations.Test;

import java.util.ArrayList;

public class OOMTest {
    @Test
    public void oomError(){
        ArrayList<Object> objects = new ArrayList<>();
        while (true) {
            objects.add(new Object());
        }
    }
}
