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
    private String value;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Link(int id,String value,String date){
        this.id = id;
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
    @Override
    public String toString(){
        return "Link : "+this.value+" and Date "+this.date;
    }
}
