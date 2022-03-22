/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;

/**
 *
 * @author d.yunenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancieList {
    @JsonProperty(value="more")
    Boolean more;
    Vacancie[] objects;
    
    Boolean getMore() {
        return this.more;
    }
    
    Vacancie[] getObjects() {
        return this.objects;
    }
    
    void setObjects(String value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        this.objects = mapper.readValue(value, Vacancie[].class);
    }
}
