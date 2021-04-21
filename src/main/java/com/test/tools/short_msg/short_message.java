package com.test.tools.short_msg;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信验证
 */
@Service
public class short_message {

    public void shor() throws Exception{
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "107683", "31d51f9b-b320-41cb-a776-fffc250b0b10");

        Map<String, Object> params = new HashMap<>();
        params.put("number","18222793967");
        params.put("templateId", "3020");
       String[] templateParams = new String[2];
       templateParams[0] = "3421";
       templateParams[1] ="5分钟";
       params.put("templateParams",templateParams);
       String result = client.send(params);
        String result1 = client.balance();
        System.out.println(result);
        System.out.println(result1);
    }

//    public static void main(String[] args) throws Exception{
//        shor();
//    }
}
