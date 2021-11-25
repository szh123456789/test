package com.test.service.tools.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class token_util {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private  final  String TOKEN_SECRET = "shuizhidaoshibushimiyao";

    Jedis jedis = new Jedis("127.0.0.1",6379);

    Long exp =7 * 24 * 3600L;

    //生成token
    public  String token (String id,String name){

        String token = "";
        try{
            //生成签名
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");

            token = JWT.create()
                    .withHeader(header)
                    .withClaim("id",id)
                    .withClaim("name",name)
                    .sign(algorithm);

            //redis判断
            if (!jedis.exists(name)) {
                jedis.set(name, token, "NX", "EX", exp);
            }else {
                jedis.set(name, token, "XX", "EX", exp);
            }
            return token;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    //验证token
    public  boolean verify(String token){
        try{
            Algorithm algorithm =Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String na  = jwt.getClaim("name").asString();
            System.out.println(na);
            System.out.println(jwt.getExpiresAt());
            //token验证正确,使用redis判断是否过期
            if(jedis.exists(na)){
                String n= jedis.get(na);
              if(token.equals(n)){
                  jedis.expire(na,new Long(exp).intValue());
                  return true;
              }else {
                  return false;
              }
            }else {
                return false;
            }


        }catch (Exception e){

            return false;
        }
    }
}
