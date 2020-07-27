package com.marstalk.jdk8.objectlayout;

import org.openjdk.jol.info.ClassLayout;
import org.testng.annotations.Test;

public class InstanceLayout {
    class Foo {
        Foo f;
    }

    @Test
    public void referenceLength() {
        System.out.println(ClassLayout.parseInstance(new Foo()).toPrintable());

    }
}
