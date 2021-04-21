package com.test.tools.token;


import com.test.tools.short_msg.short_message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class sho {

    @Autowired
    public short_message sm;

    static{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Test
    public void sh() throws Exception{
        sm.shor();
    }
}
