package com.marstalk.designpattern.observer;

public class ObserverTest {
    public static void main(String[] args) {
        Student s1 = new Student("Zhang San");
        Student s2 = new Student("Li Si");
        Teacher t1 = new Teacher("Miss Liu");
        Teacher t2 = new Teacher("Mr Wang");
        t1.addObserver(s1);
        t1.addObserver(s2);
        t1.addObserver(new Mother(s1));

        t2.addObserver(s1);

        t1.setHomework("English reading");
        t1.setHomework("English Writing");
        t2.setHomework("Physical Exercise");
    }
}
