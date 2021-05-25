package com.test.tools.token;


import com.test.tools.short_msg.short_message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class sho {

    @Autowired
    public short_message sm;
    @Autowired
    private JdbcTemplate jt;

    static{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Test
    public void sh() throws Exception{
//        sm.shor("18222793967");
//        System.out.println(sm.bl("18222793967"));



//String s="4G0yCYxOnmeA8mGIQqwCk0";


//            List<Map<String,Object>> li= jt.queryForList("select path  from file where file_key='"+s+"'");
//        System.out.println(li.get(0).get("path").toString());

        String s="/home/szh/fi/08丨Netty怎么切换三种I-O模式？.8";
        System.out.println(s.split("\\."));
        String[] sts =s.split("\\.");
        System.out.println(sts.length);
        System.out.println(sts[0]);
        System.out.println(sts[1]);
    }
}
