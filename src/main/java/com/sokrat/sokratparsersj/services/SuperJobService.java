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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author d.yunenko
 */
@Service
public class SuperJobService {
    
    @Autowired
    Environment env;
    
    public String sendCode(String code) throws IOException, URISyntaxException {

      StringBuffer content = new StringBuffer();
      String strurl = String.format("%s/vacancies/?catalogues=33", env.getProperty("SJ_API"));
      URL url = new URL(strurl);
      HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("X-Api-App-Id", env.getProperty("SJ_KEY"));
      con.setDoOutput(true); 
      con.setDoInput(true); 
      con.setRequestProperty("charset", "utf-8");
      try(BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream(), "UTF-8"))) {
          String inputLine;
          while ((inputLine = in.readLine()) != null) {
              content.append(inputLine);
          }
      }
      con.disconnect();

      return content.toString();
    }
}
