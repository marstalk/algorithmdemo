package com.marstalk.micrometer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * SimpleMeterRegistry是micrometer提供的简单registry，将数据保存在本地内存中。常用于测试。
 *
 * @author shanzhonglaosou
 */
public class MicrometerDemo1 {

    public static void main(String[] args) {
        MeterRegistry meterRegistry = new SimpleMeterRegistry();
        Counter myCounter = meterRegistry.counter("myCounter");
        for (int i = 0; i < 100; i++) {
            myCounter.increment();
        }
        System.out.println(meterRegistry.counter("myCounter").count());//100
    }

}
