package com.test.tools.video.controller;


import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * 获取视频流
 */
@RestController
public class VideoController {


    @RequestMapping("/get")
    public void play(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.reset();
        File file = new File("/home/szh/视频/netty/11丨源码剖析：Netty对Reactor的支持.mp4");
        Long fileLength = file.length();

        //随机读文件
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");

        String rangeString = request.getHeader("Range");
        String lt = request.getHeader("If-Modified-Since");
        long range =0;
        if (StrUtil.isNotBlank(rangeString)){
            range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
        }

        String t =ti();
        OutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Type", "video/mp4");
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

        randomAccessFile.seek(range);

        byte[] bytes = new byte[1024*1024];
        int len  = randomAccessFile.read(bytes);

        response.setContentLength(len);
        response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);

        outputStream.write(bytes,0,len);
//        outputStream.flush();
        outputStream.close();
        randomAccessFile.close();

        System.out.println("返回数据区间:【"+range+"-"+(range+len)+"】");
    }

    public String ti(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE,d-MMM-yyyy HH:mm:ss z ", Locale.ENGLISH);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, 1);
        rightNow.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
//        System.out.println(reStr);
        return reStr;
    }
}
