package com.marstalk.api.tencent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tencent/ocr")
public class OCRApi {
    String appId = "2135353554";


    @PostMapping("/image")
    public XMInfo ocr(@RequestParam MultipartFile file) throws IOException {
        long start = System.currentTimeMillis();
        Map<String, Object> params = new HashMap<>();
        //dongguangyao
        params.put("app_id", appId);
        params.put("time_stamp", System.currentTimeMillis() / 1000);
        params.put("nonce_str", Math.random());

        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = file.getInputStream();
            data = new byte[is.available()];
            is.read(data);
            result = Base64.getEncoder().encodeToString(data);

        }finally {
            if (null != is){
                is.close();
            }
        }

        params.put("image", result);
        params.put("sign", TencentAISignHolder.getSignature(params));
        ResponseEntity<OCRData> entity = HttpRequestUtils.post(
                "https://api.ai.qq.com/fcgi-bin/ocr/ocr_generalocr",
                params, null);
        System.out.println(entity);
        long end = System.currentTimeMillis();
        System.out.println("请求时间：" + (end - start));
        System.out.println(entity);

        XMInfo info = new XMInfo();
        List<Item> itemList = entity.getData().getItem_list();
        for (int i = 0; i < itemList.size(); i++) {
            if (i == itemList.size() -1){
                break;
            }
            Item item = itemList.get(i);
            String itemstring = item.getItemstring();
            Item nextItem = itemList.get(i + 1);
            String nextItemString = nextItem.getItemstring();
            System.out.println(itemstring);

            /**
             * 处理左边label缺失或者不存在的情况。
             * 处理右边值缺失的情况：名字地址无法补齐，其他地址可以通过字典匹配解决。
             * 减少签名的干扰。
             */
            if (itemstring.equals("姓名")) {
                info.setName("姓名: " + nextItemString);
            } else if (itemstring.equals("登記住址")) {
                if (!nextItemString.equals("立法會地方選區")){
                    info.setAddr("登記住址:" + nextItemString + itemList.get(i+2).getItemstring());
                }else{
                    info.setAddr("登記住址:" + nextItemString);
                }

            }else if (itemstring.equals("立法會地方選區")){
                info.setArea1("立法會地方選區: " + nextItemString);
            }else if (itemstring.equals("區域")){
                info.setArea2("區域: " + nextItemString);
            }else if (itemstring.equals("區議會選區")){
                info.setArea3("區議會選區: " + nextItemString);
            }else if (itemstring.equals("功能界別")){
                info.setFuncArea("功能界別: " + nextItemString);
            }else if (itemstring.equals("界別分組")){
                if (nextItemString.equals("選舉委員會")){
                    info.setAreaGroup("界別分組: -");
                }else{
                    info.setAreaGroup("界別分組: " + nextItemString);
                }
            }else if (itemstring.equals("選舉委員會")){
                if (nextItemString.startsWith("你現時已登記")){
                    info.setCommitee("選舉委員會: -");
                }else{
                    info.setCommitee("選舉委員會: " + nextItemString);
                }
            }
        }

        return info;
    }

}
