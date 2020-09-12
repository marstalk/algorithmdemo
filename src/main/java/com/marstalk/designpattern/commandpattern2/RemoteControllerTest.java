package com.marstalk.designpattern.commandpattern2;

/**
 * 这是命令模式的客户
 */
public class RemoteControllerTest {
    public static void main(String[] args) {
        SimpleRemoteController simpleRemoteController = new SimpleRemoteController();

        /**
         * 我想通过控制器把客厅那个Led灯打开。
         * 但是这个遥控器只有一个按钮即buttonWasPress()
         */
        LightOnCommand lightOnCommand = new LightOnCommand(new LEDLight());
        simpleRemoteController.setCommand(lightOnCommand);
        simpleRemoteController.buttonWasPressed();

        simpleRemoteController.setCommand(new GarageDoorOpenCommand(new GarageDoor()));
        simpleRemoteController.buttonWasPressed();

        simpleRemoteController.setCommand(new StereoOnCommand(new Stereo()));
        simpleRemoteController.buttonWasPressed();

        //TODO 当前遥控器只有一个插槽，所以每次要遥控不同的设备，要把对应的命令烧录进去。
        //而如果这个遥控器有多个插槽Command[]而不是Command。buttonWasPress(slot)。

        //后期如果有新的设备加入进来，只需要提供命令然后烧录进取即可，而不需要修改遥控器。
    }
}
