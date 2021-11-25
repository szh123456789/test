package com.test.service.loading.http.upload;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class uplhandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers responses = httpExchange.getResponseHeaders();
        String response = "ok";
        responses.set("Content-Length",String.valueOf(response.length()));
        InputStream in = httpExchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String temp = null;
        while((temp = reader.readLine()) != null){
            System.out.println("client request:" + temp);
        }

        httpExchange.sendResponseHeaders(200,response.length());
        OutputStream out = httpExchange.getResponseBody();
        out.write(response.getBytes());
        out.flush();
        httpExchange.close();

    }

}
