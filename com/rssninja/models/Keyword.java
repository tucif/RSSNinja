/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.models;

/**
 *
 * @author unicorn
 */
public class Keyword {
    private int id;
    private String value;

    public Keyword(int id, String value){
        this.id = id;
        this.value = value;
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
        return "Keyword : "+this.value;
    }
}
