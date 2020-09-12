package com.marstalk.designpattern.commandpattern2;

public class GarageDoor {
    public void up(){
        System.out.println(this.getClass().getSimpleName() + " up()");
    }
    public void down(){
        System.out.println(this.getClass().getSimpleName() + "down()");
    }
    public void stop(){
        System.out.println(this.getClass().getSimpleName() + "stop()");
    }
    public void lightOn(){
        System.out.println(this.getClass().getSimpleName() + "lightOn()");
    }
    public void lightOff(){
        System.out.println(this.getClass().getSimpleName() + "lightOff()");
    }
}
