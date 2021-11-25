package com.test.controller.file;


import cn.hutool.core.util.StrUtil;
import com.test.service.tools.heartbreak.heartbreakserver;
import com.test.service.tools.heartbreak.heartclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 获取视频流
 */

@RestController
public class VideoController {

    @Autowired
    private JdbcTemplate jt;

    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    String sp="";


    public  heartbreakserver hs;

    String stst;

    heartclient hc;

    @RequestMapping("/sett")
    @ResponseBody
    public String pp(String fk)throws IOException{

        List<Map<String,Object>> li= jt.queryForList("select path,suffix,size  from file where file_key='"+fk+"'");

        String  pa=li.get(0).get("path").toString();

        String si=li.get(0).get("size").toString();

        String[] sts =pa.split("\\.");

        this.setPath(sts[0]+"."+li.get(0).get("suffix").toString());
        sp=this.getPath();
        return si;
    }

    @RequestMapping("/get")
    public void play(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.reset();
        File file =new File(sp);

        Long fileLength = file.length();

        //随机读文件
        RandomAccessFile  randomAccessFile = new RandomAccessFile(file,"r");

        String rangeString = request.getHeader("Range");
        String lt = request.getHeader("If-Modified-Since");
        long range =0;
        if (StrUtil.isNotBlank(rangeString)){
            range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
        }

//        String t =ti();

            OutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-Type", "video/mp4");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

            randomAccessFile.seek(range);

            byte[] bytes = new byte[1024 * 1024];
            int len = randomAccessFile.read(bytes);

            response.setContentLength(len);
            response.setHeader("Content-Range", "bytes " + range + "-" + (fileLength - 1) + "/" + fileLength);

            outputStream.write(bytes, 0, len);

            outputStream.close();


            System.out.println("返回数据区间:【" + range + "-" + (range + len) + "】");

    }

//    public String ti(){
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE,d-MMM-yyyy HH:mm:ss z ", Locale.ENGLISH);
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(date);
//        rightNow.add(Calendar.MONTH, 1);
//        rightNow.setTimeZone(TimeZone.getTimeZone("GMT"));
//        Date dt1 = rightNow.getTime();
//        String reStr = sdf.format(dt1);
//
//        return reStr;
//    }

    @RequestMapping("/reaheart")
    public void heart() throws Exception{

        InetAddress ip4 = Inet4Address.getLocalHost();
        hc = new heartclient(ip4.getHostAddress(),9999);
        hc.bs();
    }

    @RequestMapping("/staheart")
    @ResponseBody
    public String staheart(){

      stst=  hc.run();
      return stst;
    }
}
