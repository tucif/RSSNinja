/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.models;

/**
 *
 * @author unicorn
 */
public class Link {
    private int id;
    private Keyword keyword;
    private String value;
    private String date;
    private int relevance;
    private String service;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Link(int id,Keyword keyword,String value,String date){
        this.id = id;
        this.keyword = keyword;
        this.value = value;
        this.date = date;
        this.relevance=0;
        this.service = "";
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    
    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public int getRelevance() {
        return relevance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
    @Override
    public String toString(){
        return "Link : "+this.value+" keyword: "+this.keyword + ", Relevance: "+this.getRelevance() + " from: "+ this.getService();
    }
}
