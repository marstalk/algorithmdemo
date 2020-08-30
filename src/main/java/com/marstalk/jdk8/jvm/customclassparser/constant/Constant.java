package com.marstalk.jdk8.jvm.customclassparser.constant;

import java.util.List;

public abstract class Constant {
    protected short tag;

    public short tag() {
        return this.tag;
    }

    public abstract void nest(List<Constant> constants);
}
