package com.marstalk.concurrent.chapter1;

/**
 * Created by louisliu on 10/31/19.
 */
public class TryConcurrency {

    public static void main(String[] args) {
//        readFromDataBase();
//        writeDatatoFile();

        new Thread("read-thread"){
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        new Thread("write-thread"){
            @Override
            public void run() {
                writeDatatoFile();
            }
        }.start();
    }


    private static void readFromDataBase() {
        //read data from database and handle it
        try {
            println("Begin read data from db.");
            Thread.sleep(1000 * 1000L);
            println("Read data done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void writeDatatoFile(){
        //read data from database and handle it
        try {
            println("Begin write data to file");
            Thread.sleep(1000 * 100L);
            println("Write data done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void println(String msg) {
        System.out.println(msg);
    }

}
