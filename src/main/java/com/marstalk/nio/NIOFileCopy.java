package com.marstalk.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * java.io.IOException: Access is denied
 *
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class NIOFileCopy {
    private static final String mac_file = "/Users/louisliu/Downloads/atlassian-confluence-6.15.9-x64.exe";
    private static final String windows_file = "D:\\backup\\atlassian-confluence-7.4.1-x64.exe";

    public static final String SOURCE_FILE_NAME = mac_file;
    public static final String DEST_FILE_NAME = mac_file;

    public static void main(String[] args) throws IOException, RunnerException {
        Options options = new OptionsBuilder().include(NIOFileCopy.class.getSimpleName()).warmupIterations(5).measurementIterations(5).forks(1).build();
        new Runner(options).run();
    }

    @Benchmark
    public void nio() throws IOException {
        copyWithNIO(SOURCE_FILE_NAME, DEST_FILE_NAME + UUID.randomUUID());
    }

    @Benchmark
    public void bio() throws IOException {
        copyWithBIO(SOURCE_FILE_NAME, DEST_FILE_NAME + UUID.randomUUID());
    }

    private static void copyWithNIO(String source, String dest) throws IOException {
        File file = Paths.get(dest).toFile();
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        try (FileChannel sourceChannel = FileChannel.open(Paths.get(source), StandardOpenOption.READ);
                        FileChannel destChannel = FileChannel.open(Paths.get(dest), StandardOpenOption.APPEND)) {
            // transerFrom是令拷贝技术的使用，文件不需要读取到用户空间，而是直接从磁盘读取到内核空间然后到磁盘。
            // 非令拷贝的路径:磁盘->内核空间->用户空间->内核空间->磁盘
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
    }

    private static void copyWithBIO(String source, String dest) throws IOException {
        File destFile = new File(dest);
        if (destFile.exists()) {
            destFile.delete();
        }
        destFile.createNewFile();
        try (FileInputStream fis = new FileInputStream(new File(source)); FileOutputStream fos = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

}
