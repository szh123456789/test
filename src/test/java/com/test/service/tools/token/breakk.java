package com.test.service.tools.token;


import com.test.service.tools.heartbreak.heartbreakserver;
import com.test.service.tools.heartbreak.heartclient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.Inet4Address;
import java.net.InetAddress;

@RunWith(SpringRunner.class)
@SpringBootTest
public class breakk {

    static{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }





    @Test
    public void t() throws Exception{
        heartbreakserver hs = new heartbreakserver(9999);
        hs.startServer();

    }

    @Test
    public void s() throws  Exception{
        InetAddress ip4 = Inet4Address.getLocalHost();
        heartclient hc = new heartclient(ip4.getHostAddress(),9999);
        hc.bs();
        while (true){
            hc.run();
            Thread.sleep(10000);
        }
//        Thread.sleep(3000);

    }
}
