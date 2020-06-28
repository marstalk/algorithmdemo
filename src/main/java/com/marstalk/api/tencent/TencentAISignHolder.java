package com.marstalk.api.tencent;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class TencentAISignHolder {

    // ================================================================
    // Constants
    // ================================================================

    // ================================================================
    // Fields
    // ================================================================

//    private static final TencentAiConfig CONFIG = SpringContextHolder.getBean(TencentAiConfig.class);

    // ================================================================
    // Constructors
    // ================================================================

    // ================================================================
    // Methods from/for super Interfaces or SuperClass
    // ================================================================

    // ================================================================
    // Public or Protected Methods
    // ================================================================

    /**
     * SIGN签名生成算法-JAVA版本 通用。默认参数都为UTF-8适用
     *
     * @param params 请求参数集，所有参数必须已转换为字符串类型
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(Map<String, Object> params) throws IOException {
        String appKey = "1y1Ili0xHlIPYM3G";

        Map<String, Object> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            if (param.getValue() != null && !"".equals(param.getKey().trim()) &&
                    !"sign".equals(param.getKey().trim()) && !"".equals(param.getValue())) {
                baseString.append(param.getKey().trim()).append("=")
                        .append(URLEncoder.encode(param.getValue().toString(), "UTF-8")).append("&");
            }
        }
        if (baseString.length() > 0) {
            baseString.deleteCharAt(baseString.length() - 1).append("&app_key=")
                    .append(appKey);
        }
        try {
            return MD5.getMD5(baseString.toString());
//            return new String(md5s);
//            String sign = MD5.md5(baseString.toString());
//            System.out.println("sign:" + sign.toUpperCase());
//            return sign.toUpperCase();
        } catch (Exception ex) {
            throw new IOException(ex);
        }
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
