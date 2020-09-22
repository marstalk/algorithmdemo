package com.marstalk.io.bio;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * File receiver and saved to machine with blocking IO
 */
public class FileRevieverBIO {
    private static String RECEIVE_FOLDER = "/Users/louisliu/IdeaProjects/algorithmdemo/src/main/java/com/marstalk/io/bio/";

    public static void main(String[] args) throws IOException {
        AtomicInteger ai = new AtomicInteger();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                return new Thread(r, "working thread" + ai.getAndIncrement());
            }
        });
        ServerSocket ss = new ServerSocket(8099);
        while (true) {
            Socket accept = ss.accept();
            es.submit(() -> {
                try (InputStream inputStream = accept.getInputStream()) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
