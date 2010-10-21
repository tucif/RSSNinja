package com.rssninja;

import jadex.runtime.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import jadex.adapter.fipa.SFipa;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AprendizDelicious extends Plan {

    public AprendizDelicious() {
        System.out.println("AprendizDelicious creado");
    }

    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();

        String popularUrlString="http://feeds.delicious.com/v2/json/popular/"+tag+"?count=4";
        String recentUrlString="http://feeds.delicious.com/v2/json/tag/"+tag+"?count=4";

        JSONArray popularLinks = getJSONFromURL(popularUrlString);
        JSONArray recentLinks = getJSONFromURL(recentUrlString);

        sendMessagesToNinja(popularLinks, message);
        sendMessagesToNinja(recentLinks,message);
    }

    public JSONArray getJSONFromURL(String urlString){
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

    public void sendMessagesToNinja(JSONArray jsonArray, IMessageEvent message){
        String urlString=null;
        JSONObject currentObject= null;
        JSONObject objectToSend=null;
        JSONObject metaData = null;
        
        for(int i=0; i<jsonArray.size(); i++){
            objectToSend=new JSONObject();
            currentObject= (JSONObject)jsonArray.get(i);

            urlString= currentObject.get("u").toString();

            objectToSend.put("service","delicious");
            objectToSend.put("link",urlString);
            objectToSend.put("content",getContentFromURL(urlString));

            //generate Meta
            metaData=new JSONObject();
            metaData.put("type","html");
            metaData.put("title",currentObject.get("d").toString());
            metaData.put("date",currentObject.get("dt").toString());
            metaData.put("relatedTags",currentObject.get("t").toString());

            objectToSend.put("meta",metaData.toString());
            System.out.println("metadata: "+metaData.toString());

            sendMessage(message.createReply("inform",objectToSend.toString()));

        }
    }

    public String getContentFromURL(String urlString){
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

}
