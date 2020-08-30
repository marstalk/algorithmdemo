package com.marstalk.algorithmdemo.dynamicprogramming;

import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPrepare {

    static String STEREO = "stereo";
    static String LAPTOP = "laptop";
    static String GUITAR = "guitar";
    static String IPHONE = "iphone";
    static String RING   = "ringgg";
    static float BAG_SIZE;

    static Map<String, Integer> vMap = new HashMap<>();
    static Map<String, Float> wMap = new HashMap<>();
    List<String> goods = new ArrayList<>();

    static {
        vMap.put(STEREO, 3000);
        vMap.put(LAPTOP, 2000);
        vMap.put(GUITAR, 1500);
        vMap.put(IPHONE, 2000);
        vMap.put(RING, 1000);

        wMap.put(STEREO, 4.0F);
        wMap.put(LAPTOP, 3.0F);
        wMap.put(GUITAR, 1F);
        wMap.put(IPHONE, 1F);
        wMap.put(RING, 0.5F);
    }

    @BeforeMethod
    public void beforeMethod(){
        case1();
    }

    private void case1() {
        BAG_SIZE = 6.3F;
        goods.add(RING);
        goods.add(STEREO);
        goods.add(LAPTOP);
        goods.add(GUITAR);
        goods.add(IPHONE);
    }

}
