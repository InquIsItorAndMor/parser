package com.sokrat.sokratparsersj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sokrat.sokratparsersj.models.Catalogues;
import com.sokrat.sokratparsersj.models.DownloadFile;
import com.sokrat.sokratparsersj.models.Vacancie;
import com.sokrat.sokratparsersj.models.VacancieList;
import com.sokrat.sokratparsersj.services.Download;
import com.sokrat.sokratparsersj.services.SuperJobService;
import com.sokrat.sokratparsersj.util.Excel;
import com.sokrat.sokratparsersj.util.JsonModal;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/rest")
public class RestController {

    @Autowired
    SuperJobService serviceSJ;
    
    @Autowired
    JsonModal jsonModal;
    
    @Autowired
    Environment env;
    
    @RequestMapping(value="/downloadExcel", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadExcel(@RequestParam(value = "category") String category) 
            throws IOException, Exception{
        String resp = "";
        DownloadFile dFile = null;
        List<Vacancie> vacancies = new ArrayList<>();
        Integer pageNumber = 1;
        Excel excel = new Excel(env);
        try {
            while(true) {
                resp = serviceSJ.getVacanciesFromPage(Integer.parseInt(category), pageNumber).toString();
                VacancieList vList = jsonModal.jsonToModalVacancieList(resp);
                vacancies.addAll(vList.getObjects());
                if(!vList.getMore()) {
                    break;
                } else {
                    pageNumber++;   
                }
            }
            
            dFile = excel.writeIntoExcel(vacancies, category);

        } catch (URISyntaxException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Download(dFile).download();
    }
    
    @RequestMapping(value="/getAllVacancie", method = RequestMethod.GET,  produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAllVacancie(@RequestParam(value = "category") String category) throws IOException, Exception{
        String resp = "";
        String json = "[{}]";
        List<Vacancie> vacancies = new ArrayList<>();
        Integer pageNumber = 1;
        try {
            while(true) {
                resp = serviceSJ.getVacanciesFromPage(Integer.parseInt(category), pageNumber).toString();
                VacancieList vList = jsonModal.jsonToModalVacancieList(resp);
                vacancies.addAll(vList.getObjects());
                if(!vList.getMore()) {
                    break;
                } else {
                    pageNumber++;   
                }
            }
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(vacancies);

        } catch (URISyntaxException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    
    @RequestMapping(value="/getAllCatalogues", method = RequestMethod.GET,  produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAllCatalogues() throws IOException, Exception{
        String resp = "";
        String json = "[{}]";
        List<Catalogues> catalogues = new ArrayList<>();
        
        try {
            resp = serviceSJ.getCatalogues().toString();
            catalogues = jsonModal.jsonToModalCatalogues(resp);             
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(catalogues);

        } catch (URISyntaxException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
