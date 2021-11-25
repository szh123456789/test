package com.test;

import com.test.service.tools.heartbreak.heartbreakserver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication()
@MapperScan("com.test")
public class start {

    public static heartbreakserver hs;
    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<>();
//        List<String> li = new ArrayList<>();
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(start.class, args);
        hs=new heartbreakserver(9999);
        hs.startServer();
//            String url ="http://localhost:8080/S_Land";
//            Runtime runtime = Runtime.getRuntime();
//            try{
//                runtime.exec("curl "+url);
//            }catch (Exception e){
//                e.printStackTrace();
//            }

    }
}
