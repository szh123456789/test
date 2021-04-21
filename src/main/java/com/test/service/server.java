package com.test.service;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.IOException;
import java.net.InetSocketAddress;

public class server {
    public void upload() throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        HttpServer server  = provider.createHttpServer(new InetSocketAddress(7878),100);
        server.createContext("/upl",new upload());
        server.setExecutor(null);
        server.start();
        System.out.println("httpserver start");
    }
}
