package com.marstalk.basic.serializable;

import com.alibaba.fastjson.JSONObject;

public class SerializableDemo {

    public static void main(String[] args) {
        Man man = new Man();
        man.setId(2);
        man.setName("ljc");
        System.out.println(man.toString());

        String s = JSONObject.toJSONString(man);

        Man man1 = JSONObject.parseObject(s, Man.class);
        System.out.println(man1);


    }
}
