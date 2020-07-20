package com.marstalk.api.tencent;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

public final class HttpRequestUtils<T> {

    // ================================================================
    // Constants
    // ================================================================

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtils.class);

    // ================================================================
    // Fields
    // ================================================================

//    private static final Gson GSON = SpringContextHolder.getBean(Gson.class);


    // ================================================================
    // Constructors
    // ================================================================

    // ================================================================
    // Methods from/for super Interfaces or SuperClass
    // ================================================================

    // ================================================================
    // Public or Protected Methods
    // ================================================================

    public static <T> ResponseEntity<T> post(String url, Map<String, Object> param,
                                             Map<String, Object> header) {
        HttpResponse response =
                HttpRequest.post(url).form(param)
                        .send();
        String resp = response.bodyText();
        ResponseEntity type1 = JSONObject.parseObject(resp, ResponseEntity.class);
        return type1;
    }


    // ================================================================
    // Getter & Setter
    // ================================================================

    // ================================================================
    // Private Methods
    // ================================================================


    // ================================================================
    // Inner or Anonymous Class
    // ================================================================

    // ================================================================
    // Test Methods
    // ================================================================

}
