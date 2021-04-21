package com.test.service;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;

import java.io.*;

public class upload implements HttpHandler {

    @SneakyThrows
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
//        Headers heads = httpExchange.getResponseHeaders();
//
//        File file = new File("D:\\BaiduNetdiskDownload\\93-Netty源码剖析与实战\\11丨源码剖析：Netty对Reactor的支持.mp4");
//        InputStream inputStream =  new FileInputStream(file);
//        //下载
//        System.out.println(inputStream);
//        byte[] bytes = new byte[1024];
//        inputStream.read(bytes);
//
//        heads.set("Content-Encoding","gzip");
//        heads.set("Transfer-Encoding","chunked");
//        heads.set("Content-Type","application/mp4");
//        httpExchange.sendResponseHeaders(200,bytes.length);
//        OutputStream outputStream = httpExchange.getResponseBody();
//        BufferedInputStream in = new BufferedInputStream(inputStream);
//        BufferedOutputStream out = new BufferedOutputStream(outputStream);
//        int count;
//        while((count = inputStream.read(bytes)) != -1){
//            out.write(bytes,0,count);
//        }
//        out.flush();
//        out.close();
//        in.close();
//        httpExchange.close();

//        //拉流
        Headers heads = httpExchange.getResponseHeaders();

        File file = new File("D:\\BaiduNetdiskDownload\\93-Netty源码剖析与实战\\11丨源码剖析：Netty对Reactor的支持.mp4");
        InputStream inputStream =  new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int a = inputStream.available();
        heads.set("Content-Type","application/mp4");
        httpExchange.sendResponseHeaders(200,bytes.length);
        heads.set("Content-Encoding","gzip");
        heads.set("Transfer-Encoding","chunked");
        heads.set("Cache-Control","public");
        heads.set("Content-Length","a");
        heads.set("Content-Range","bytes "+0+"-"+(inputStream.available()-1)+"/"+inputStream.available());
        heads.set("Access-Control-Allow-Origin", "*");
        heads.set("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With, Current-Page");
        heads.set("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS");
        OutputStream outputStream = httpExchange.getResponseBody();
        BufferedInputStream in = new BufferedInputStream(inputStream);
        BufferedOutputStream out = new BufferedOutputStream(outputStream);

        int count;
        while((count = in.read(bytes)) != -1){
            out.write(bytes,0,count);
            out.flush();
        }
        bytes =null;
        out.flush();
        out.close();
        in.close();
        httpExchange.close();
    }

}