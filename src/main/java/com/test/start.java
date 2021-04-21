package com.test;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication()
@MapperScan("com.test.tools.video.dao")
public class start {
    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<>();
//        List<String> li = new ArrayList<>();
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(start.class, args);

//            String url ="http://localhost:8080/S_Land";
//            Runtime runtime = Runtime.getRuntime();
//            try{
//                runtime.exec("curl "+url);
//            }catch (Exception e){
//                e.printStackTrace();
//            }

    }
}
