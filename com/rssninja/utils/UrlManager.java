/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author zheroth
 */
public class UrlManager {
    public UrlManager(){

    }

    public static String getContentFromURL(String urlString){
        BufferedReader br = null;
        StringBuilder sb= new StringBuilder("");
        URL url=null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            //TODO: do something when this happens
            System.out.println("MalformedURL: "+urlString.toString());
        }

        try {
            br= new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException ex) {
            //TODO: do something when this happens
        }
        try {
            sb= new StringBuilder("");
            String nextLine="";
            while(true){
                nextLine=br.readLine();
                if(nextLine==null)
                    break;
                sb.append(nextLine);
            }
        } catch (IOException ex) {
            //TODO: do something when this happens
        }

        return sb.toString();
    }

    public static JSONArray getJSONArrFromURL(String urlString){
        URL url=null;
        BufferedReader br=null;
        JSONArray jsonArray = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            //TODO: do something when this happens
        }
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException ex) {
            //TODO: do something when this happens
        }
        try {
            jsonArray=(JSONArray)JSONValue.parse(br.readLine());
        } catch (IOException ex) {
            //TODO: do something when this happens
        }

        return jsonArray;
    }

    public static JSONObject getJSONObjFromURL(String urlString){
        URL url=null;
        BufferedReader br=null;
        JSONObject jsonObject = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            //TODO: do something when this happens
        }
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException ex) {
            //TODO: do something when this happens
        }
        try {
            jsonObject=(JSONObject)JSONValue.parse(br.readLine());
        } catch (IOException ex) {
            //TODO: do something when this happens
        }

        return jsonObject;
    }

}
