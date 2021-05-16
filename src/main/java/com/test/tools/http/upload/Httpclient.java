package com.test.tools.http.upload;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class Httpclient {



    public String up(String i) throws IOException {


        System.out.println("-----------------");
        URL url = new URL("http://172.18.0.40:7878/upl");
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        urlConn.setRequestMethod("POST");
        String str =i;
        OutputStream out = urlConn.getOutputStream();
        out.write(str.getBytes());
        out.flush();
        String temp = "";
        System.out.println(urlConn.getContentLength());
        System.out.println(urlConn.getHeaderFields());
        if (urlConn.getContentLength() != -1) {
            if (urlConn.getResponseCode() == 200) {
                InputStream in = urlConn.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in));

                while ((temp = read.readLine()) != null) {
                    System.err.println("server response:" + temp);
                }
                read.close();
                in.close();
            }
            urlConn.disconnect();
            System.out.println(urlConn.getContentLength());
        }
        return "111111111";
    }
}
