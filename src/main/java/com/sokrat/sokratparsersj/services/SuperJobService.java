/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Service;

/**
 *
 * @author d.yunenko
 */
@Service
public class SuperJobService {
    
      public String sendCode(String code) throws IOException, URISyntaxException {

        StringBuffer content = new StringBuffer();
        URL url = new URL("https://api.superjob.ru/2.0/vacancies/?catalogues=33");
         HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Api-App-Id", "v3.r.135578798.c8deafc0eb82be3894cdfe5bffe9c5b1e7aa1c23.f80b886cd34110e1e35a172dbe2315a609d8c7d9");
        con.setDoOutput(true); 
        con.setDoInput(true); 
        con.setRequestProperty("charset", "utf-8");

        
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        
        return content.toString();
    }
}
