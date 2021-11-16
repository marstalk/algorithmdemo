package com.marstalk.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchMarkForiTest {

    @Benchmark
    public void test() {
        System.out.println("zhongbin");
    }

    public static void main(String[] args) throws RunnerException {
        OptionsBuilder optionsBuilder = new OptionsBuilder();
        Options build = optionsBuilder.include(BenchMarkForiTest.class.getSimpleName()).warmupIterations(5).forks(1).measurementIterations(5).build();
        new Runner(build).run();
    }

}
