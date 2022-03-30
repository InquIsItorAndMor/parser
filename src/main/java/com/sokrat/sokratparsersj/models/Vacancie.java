/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    
    public List<String> getObjectToArray() {
        List<String> arrayStr = new ArrayList<String>();
        arrayStr.add(this.profession);
        arrayStr.add(this.address);
        arrayStr.add(this.candidat);
        arrayStr.add(this.maxPayment.toString() + " " + this.currency);
        arrayStr.add(this.firmName);
        arrayStr.add(this.firmDescription);
        return arrayStr;
    }
}
