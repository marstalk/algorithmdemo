package com.marstalk.interview.dingqiao;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 编译错误。
 * FileNotFoundException extends IOException extends Exception
 * catch的话，越往上的catch，就要越具体（自类）
 */
public class InterviewQuestion5Exception {
    public static void main(String[] args) {
        try {
            throw new IOException();
        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException");
        } catch (IOException exception) {
            System.out.println("IOException");
        } catch (Exception exception) {
            System.out.println("Exception");
        }
//        catch (IOException exception) {
//            System.out.println("IOException");
//        }
    }
}
