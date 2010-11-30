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
        return "Link : "+this.value+" and Date "+this.date+" keyword: "+this.keyword;
    }
}
