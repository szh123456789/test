package com.test.tools.token;


import com.test.tools.heartbreak.heartbreakserver;
import com.test.tools.heartbreak.heartclient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        heartclient hc = new heartclient("192.168.18.3",9999);
        hc.run();
    }
}
