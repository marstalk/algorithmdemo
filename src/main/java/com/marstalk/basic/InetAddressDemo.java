package com.marstalk.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());//局域网地址。192.168.3.17
        System.out.println(inetAddress.getHostName());//主机名DESKTOP-6H4H61F
    }
}
