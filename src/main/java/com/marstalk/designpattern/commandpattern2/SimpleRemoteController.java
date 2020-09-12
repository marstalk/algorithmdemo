package com.marstalk.designpattern.commandpattern2;

public class SimpleRemoteController {
    Command slot;

    public void setCommand(Command slot) {
        this.slot = slot;
    }

    public void buttonWasPressed(){
        slot.execute();
    }
}
