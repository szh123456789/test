package com.test.service.tools;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
public class time {

    static{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    @Test
    public void s(){
//        Date date = new Date();
//        DateFormat gmtDateFormat = new SimpleDateFormat("EEE,d-MMM-yyyy HH:mm:ss z ", Locale.ENGLISH);
//        Calendar cal = Calendar.getInstance();
//        //设置当前时间
//        cal.setTime(date);
//        //在当前时间基础上减一年
//        cal.add(Calendar.MONTH,1 );
//        gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//        String dateStr = gmtDateFormat.format(date);
//        System.out.println("转换为GMT：");
//        System.out.println(dateStr);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE,d-MMM-yyyy HH:mm:ss z ", Locale.ENGLISH);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, 1);
        rightNow.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        System.out.println(reStr);
    }
}
