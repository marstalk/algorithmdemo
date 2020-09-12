package com.marstalk.designpattern.commandpattern2;

public class LEDLight implements Light {
    @Override
    public void on() {
        System.out.println(this.getClass().getSimpleName() + " on()");
    }

    @Override
    public void off() {
        System.out.println(this.getClass().getSimpleName() + " off()");
    }
}
