/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.models;

/**
 *
 * @author unicorn
 */
public class Knowledge {
    
    private int id;
    private Link link;
    private String service;
    private int relevance;
    public Knowledge(int id, Link link, String service, int relevance) {
        this.id = id;
        this.link = link;
        this.service = service;
        this.relevance = relevance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    @Override
    public String toString(){
        return "Knowledge - \n keyword: "
                +"\nLink: "+this.link.toString()
                +"\nService: "+this.service.toString()
                +"\n relevance: "+this.relevance;
    }
}
