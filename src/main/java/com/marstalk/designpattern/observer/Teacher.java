package com.marstalk.designpattern.observer;

import java.util.Observable;

public class Teacher extends Observable {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public void setHomework(String homework) {
        System.out.println(this.getName() + " set homework " + homework);
        setChanged();
        notifyObservers(homework);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
