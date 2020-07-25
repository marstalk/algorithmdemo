package com.marstalk.benchmark;

import java.io.IOException;
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

@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class BenchMarkDemo1 {

    @Benchmark
    public int init() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {

        }
        return 0;
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Options options = new OptionsBuilder()
                        .include(BenchMarkDemo1.class.getSimpleName())
                        .forks(1)
                        .warmupIterations(5)
                        .measurementIterations(5).build();
        new Runner(options).run();
    }
}
