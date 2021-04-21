package com.test.tools.http.upload;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;

@Service
public class httpserver {

    public void upload() throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        HttpServer server  = provider.createHttpServer(new InetSocketAddress(7878),100);
        server.createContext("/upl",new uplhandler());
        server.setExecutor(null);
        server.start();
        System.out.println("httpserver start");
    }



}