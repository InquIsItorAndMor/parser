/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sokrat.sokratparsersj.models.Catalogues;
import com.sokrat.sokratparsersj.models.Vacancie;
import com.sokrat.sokratparsersj.models.VacancieList;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author dimay
 */
@Component
public class JsonModal {
    public VacancieList jsonToModalVacancieList(String json) throws JsonProcessingException {
        VacancieList vacancies = null;
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        vacancies = mapper.readValue(json, VacancieList.class);
        
        return vacancies;
    }
    
    public ArrayList<Catalogues> jsonToModalCatalogues(String json) throws JsonProcessingException {
        ArrayList<Catalogues> catalogues = null;
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        catalogues = mapper.readValue(json, new TypeReference<ArrayList<Catalogues>>(){});
        
        return catalogues;
    }
    
}
