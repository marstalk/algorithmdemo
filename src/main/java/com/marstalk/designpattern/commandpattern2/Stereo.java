package com.marstalk.designpattern.commandpattern2;

public class Stereo {
    public void on(){
        System.out.println(this.getClass().getSimpleName() + " on()");
    }
    public void off(){
        System.out.println(this.getClass().getSimpleName() + " off()");
    }
    public void setCD(){
        System.out.println(this.getClass().getSimpleName() + " setCD()");
    }
    public void setDvd(){
        System.out.println(this.getClass().getSimpleName() + " setDvd()");
    }
    public void setRadio(){
        System.out.println(this.getClass().getSimpleName() + " setRadio()");
    }
    public void setVolume(){
        System.out.println(this.getClass().getSimpleName() + " setVolume()");
    }
}
