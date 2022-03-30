/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.yunenko
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vacancie {
    @JsonProperty(value="address")
    String address;
    @JsonProperty(value="profession")
    String profession;
    @JsonProperty(value="candidat")
    String candidat;
    @JsonProperty(value="payment_from")
    Integer maxPayment;
    @JsonProperty(value="currency")
    String currency;
    @JsonProperty(value="firm_name")
    String firmName;
    @JsonProperty(value="firm_activity")
    String firmDescription;
    @JsonProperty(value="gender")
    Gender gender;
    @JsonProperty(value="experience")
    Experience experience;
    @JsonProperty(value="education")
    Education education;
    @JsonProperty(value="place_of_work")
    PlaceWork placeWork;
    @JsonProperty(value="type_of_work")
    TypeWork typeWork;
    
    @JsonIgnore
    ObjectMapper mapper = new ObjectMapper();
    @JsonIgnore
    Gson gson = new Gson();
    
    public Vacancie() {
        this.mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }
    
    
    String listObjectToJson(List<Object> value) {
        return gson.toJson(value.get(0));
    }
    
    @JsonProperty(value="currency")
    void setCurrency(String value) {
        if(value.equals("rub"))
        {
            this.currency = "руб";
        } else {
            this.currency = value;
        }
    }
    
    @JsonProperty(value="gender")
    void setGender(List<Object> value) throws JsonProcessingException {
        String json = listObjectToJson(value);
        this.gender = mapper.readValue(json, Gender.class);
    }
    
    @JsonProperty(value="experience")
    void setExperience(List<Object> value) throws JsonProcessingException {
        String json = listObjectToJson(value);
        this.experience = mapper.readValue(json, Experience.class);
    }
    
    @JsonProperty(value="education")
    void setEducation(List<Object> value) throws JsonProcessingException {
        String json = listObjectToJson(value);
        this.education = mapper.readValue(json, Education.class);
    }
    
    @JsonProperty(value="place_of_work")
    void setPlaceWork(List<Object> value) throws JsonProcessingException {
        String json = listObjectToJson(value);
        this.placeWork = mapper.readValue(json, PlaceWork.class);
    }
    
    @JsonProperty(value="type_of_work")
    void setTypeWork(List<Object> value) throws JsonProcessingException {
        String json = listObjectToJson(value);
        this.typeWork = mapper.readValue(json, TypeWork.class);
    }
    
    public String getAddress() {
        return this.address;
    };
    public String getProfession() {
        return this.profession;
    };
    public String getCandidat() {
      return this.candidat;  
    };
    public Integer getMaxPayment() {
        return this.maxPayment;
    };
    public String getCurrency() {
        return this.currency;
    };
    public String getFirmName() {
        return this.firmName;
    };
    public String getFirmDescription() {
        return firmDescription;
    };
    
    @JsonIgnore
    public List<String> getObjectToArray() {
        List<String> arrayStr = new ArrayList<String>();
        arrayStr.add(this.profession);
        arrayStr.add(this.address);
        arrayStr.add(this.candidat);
        arrayStr.add(this.maxPayment.toString());
        arrayStr.add(this.currency);
        arrayStr.add(this.firmName);
        arrayStr.add(this.firmDescription);
        String tag = String.format("Пол: %s, Опыт: %s, Образование: %s, Тип занятости: %s %s", 
                this.gender.title, this.experience.title, this.education.title, this.placeWork.title, this.typeWork.title);
        arrayStr.add(tag);
        return arrayStr;
    }
}
