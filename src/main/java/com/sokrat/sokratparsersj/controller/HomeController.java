package com.sokrat.sokratparsersj.controller;

import com.sokrat.sokratparsersj.services.SuperJobService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    SuperJobService serviceSJ;
    
    @RequestMapping(value="/")
    public ModelAndView test(HttpServletResponse response) throws IOException{
       String resp = "";
        Map data = new HashMap();
        try {
            resp = serviceSJ.sendCode(33, 1);
            JSONObject jsonObject = new JSONObject(resp);
            
            data.put("sj", jsonObject.toString());

        } catch (URISyntaxException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView("home", data);
    }
}
