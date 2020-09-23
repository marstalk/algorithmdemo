package com.marstalk.jdk8.jvm.opcode;

import com.marstalk.benchmark.demo2.SecondBenchmark;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class EqualsBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(EqualsBenchmark.class.getSimpleName()).forks(2).warmupIterations(5).measurementIterations(5).build();
        new Runner(options).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void eq1(){
        String str = "str";
        if (str == null) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void eq2(){
        String str = "str";
        if (null == str) {

        }
    }

}
