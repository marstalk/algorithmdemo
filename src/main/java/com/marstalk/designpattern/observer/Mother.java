package com.marstalk.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Mother implements Observer {

    private Student child;

    public Mother(Student child) {
        this.child = child;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(child.getName() + "'s mother copy that, and will company child to do homework");
    }
}
