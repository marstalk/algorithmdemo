package com.marstalk.testng;

import org.springframework.stereotype.Component;

@Component
public class NgDao {

    public String hi(String name) {
        String hi = "hi " + name + "from ngDao";
        System.out.println(hi);
        return hi;
    }

    public String queryByName(String name) {
        System.out.println("ngDao queryByName fromDB");
        return "Shanzhonglaosou";
    }
}
