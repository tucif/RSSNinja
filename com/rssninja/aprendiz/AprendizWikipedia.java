package com.rssninja.aprendiz;

import jadex.runtime.*;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.simple.*;
import jadex.adapter.fipa.SFipa;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AprendizWikipedia extends Plan {

    public AprendizWikipedia() {
        System.out.println("AprendizWikipedia creado");
    }

    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();

        String url="http://en.wikipedia.org/w/index.php?action=raw&title="+tag;

        String wikiText = getContentFromURL(url);

        JSONObject objectToSend=new JSONObject();
        objectToSend.put("service","wikipedia");
        objectToSend.put("link",url);
        objectToSend.put("content",wikiText);

        //generate Meta
        JSONObject metaData=new JSONObject();
        metaData.put("type","wikitext");
        objectToSend.put("meta",metaData.toString());

        sendMessage(message.createReply("inform",objectToSend.toString()));

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
