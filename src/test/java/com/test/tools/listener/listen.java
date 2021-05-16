package com.test.tools.listener;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class listen {



    @Resource
    public MethodMonitorEventPublisher me;


    static{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Test
    public void sa() throws Exception{
        me.addEventListener(new AbstractMethodMonitorEventListener());
        me.methodMonitor();
    }
}
