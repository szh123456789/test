package com.test.in;


import checkers.units.quals.min;
import com.test.min.ind;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class de {

    static{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    @Autowired
    private ind m;

    @Test
    public void te(){
      Map<String,List<Map<String,Object>>> mp= m.inn();
        System.out.println(mp.get("mem").get(1).get("na"));
        System.out.println(mp);
    }
}
