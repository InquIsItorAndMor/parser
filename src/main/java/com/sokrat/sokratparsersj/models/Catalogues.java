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
 * @author dimay
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalogues {
    @JsonProperty(value="key")
    Integer key;
    @JsonProperty(value="title_rus")
    String title;
    
    public Integer getKey() {
        return this.key;
    }
    
    public String getTitle() {
        return this.title;
    }
}
