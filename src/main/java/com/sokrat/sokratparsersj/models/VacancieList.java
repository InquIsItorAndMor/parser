/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.yunenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancieList {
    @JsonProperty(value="more")
    Boolean more;
    ArrayList<Vacancie> objects;
    
    public Boolean getMore() {
        return this.more;
    }
    
    public ArrayList<Vacancie> getObjects() {
        return this.objects;
    }
    @JsonProperty(value="objects")
    void setObjects(List<Object> value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        Gson gson = new Gson();
        String json = gson.toJson(value);
        this.objects = mapper.readValue(json, new TypeReference<ArrayList<Vacancie>>(){});
      //System.out.print(value.toString());
    }
}
