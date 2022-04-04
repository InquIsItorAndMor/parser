/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.models;

/**
 *
 * @author d.yunenko
 */
public class DownloadFile {
    String fileName;
    String path;
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setFileName(String value) {
        this.fileName = value;
    }
    
    public void setPath(String value) {
        this.path = value;
    }
}
