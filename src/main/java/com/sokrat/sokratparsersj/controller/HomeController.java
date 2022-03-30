package com.sokrat.sokratparsersj.controller;

import com.google.gson.Gson;
import com.sokrat.sokratparsersj.models.Vacancie;
import com.sokrat.sokratparsersj.models.VacancieList;
import com.sokrat.sokratparsersj.services.SuperJobService;
import com.sokrat.sokratparsersj.util.Excel;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    SuperJobService serviceSJ;
    
    @Autowired
    Environment env;
    
    @RequestMapping(value="/")
    public ModelAndView test(HttpServletResponse response) throws IOException, Exception{
        String resp = "";
        Map data = new HashMap();
        List<Vacancie> vacancies = new ArrayList<Vacancie>();
        Integer pageNumber = 1;
        Excel excel = new Excel(env);
        try {
            while(true) {
                resp = serviceSJ.getVacanciesFromPage(33, pageNumber).toString();
                VacancieList vList = serviceSJ.jsonToModal(resp);
                vacancies.addAll(vList.getObjects());
                if(!vList.getMore()) {
                    break;
                } else {
                    pageNumber++;   
                }
            }
            
            excel.writeIntoExcel(vacancies);
            Gson gson = new Gson();
            data.put("sj", gson.toJson(vacancies));

        } catch (URISyntaxException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView("home", data);
    }
}
