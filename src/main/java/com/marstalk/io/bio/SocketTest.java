package com.marstalk.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(200);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println(">>>Step1: Starts to listen port: 8090");
        while (true) {
            //Thread block until socket connected
            Socket socket = serverSocket.accept();
            System.out.println(">>>Step2, client connect on port " + socket.getPort());
            executorService.submit(() -> {
                try {
                    //Thread block until socket get some message.
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        String line = reader.readLine();
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
