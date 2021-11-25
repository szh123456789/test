package com.test.service.tools.short_msg;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 短信验证
 */
@Service
public class short_message {

    Jedis jedis = new Jedis("127.0.0.1",6379);
    public void shor(String number) throws Exception{
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "107683", "31d51f9b-b320-41cb-a776-fffc250b0b10");

        Map<String, Object> params = new HashMap<>();
        params.put("number",number);
        params.put("templateId", "3020");
       String[] templateParams = new String[2];
       templateParams[0] = yy();
       templateParams[1] ="5分钟";
       params.put("templateParams",templateParams);

//       String result = client.send(params);
//        String result1 = client.balance();
//        System.out.println(result);
//        System.out.println(result1);
//        System.out.println(number);

        String sts =templateParams[0];
        if (!jedis.exists(number)){
            jedis.set(number,sts,"NX","EX",300L);
        }else{
            jedis.set(number,sts,"NX","EX",300L);
        }

    }

    public String yy(){
        String str="abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        Random r=new Random();
        String arr[]=new String [4];
        String b="";
        for(int i=0;i<4;i++)
        {
            int n=r.nextInt(62);

            arr[i]=str.substring(n,n+1);
            b+=arr[i];

        }
        return b;
    }

    public String bl(String tm){
         String t= jedis.get(tm);
         if (t!=null){
             return t;
         }else{
             return null;
         }
    }
}
