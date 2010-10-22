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

    public static String getContentFromURL(String urlString){
         StringBuilder sb= new StringBuilder("");

        BufferedReader br=createBufferedReaderFromUrl(urlString);
        
        try {
            String nextLine="";
            while(true){
                nextLine=br.readLine();
                if(nextLine==null)
                    break;
                sb.append(nextLine);
            }
        } catch (IOException ex) {
            //TODO: do something when stringbuilder fails
            System.out.println("IOException: "+ex);
        }

        return sb.toString();
    }

    public static JSONArray getJSONArrFromURL(String urlString){
        JSONArray jsonArray = null;

        BufferedReader br=createBufferedReaderFromUrl(urlString);
        
        try {
            jsonArray=(JSONArray)JSONValue.parse(br.readLine());
        } catch (IOException ex) {
            //TODO: do something when this happens
        }

        return jsonArray;
    }

    public static JSONObject getJSONObjFromURL(String urlString){
        JSONObject jsonObject = null;

        BufferedReader br=createBufferedReaderFromUrl(urlString);
        
        try {
            jsonObject=(JSONObject)JSONValue.parse(br.readLine());
        } catch (IOException ex) {
            //TODO: do something when this happens
        }

        return jsonObject;
    }

    private static BufferedReader createBufferedReaderFromUrl(String urlString){
        URL url=null;
        BufferedReader br=null;
        
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
        return br;
    }

}
