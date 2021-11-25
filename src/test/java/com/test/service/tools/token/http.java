package com.test.service.tools.token;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;


@RunWith(SpringRunner.class)
@SpringBootTest
public class http {

//    @Autowired
//    public server se;
//
//    @Autowired
//    public client cl;

    static{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

//    public void s() throws Exception{
//        se.upload();
//        cl.up();
//    }
    @Test
    public void se(){
        File file = new File("/home/szh/视频/netty/11丨源码剖析：Netty对Reactor的支持.mp4");
        long fileLength = file.length();
        System.out.println(fileLength);
    }
}
