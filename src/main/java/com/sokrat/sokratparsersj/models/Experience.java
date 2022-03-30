/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author d.yunenko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Experience {
    @JsonProperty(value = "title")
    String title;
    
    public String getTitle() {
        return this.title;
    }
}
