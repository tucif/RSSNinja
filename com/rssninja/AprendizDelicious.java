package com.rssninja;
//import java.util.*;

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

        System.out.println("service=Delicious");
        System.out.println("tag="+tag);
        System.out.println();

        String popularUrlString="http://feeds.delicious.com/v2/json/popular/"+tag;
        String recentUrlString="http://feeds.delicious.com/v2/json/tag/"+tag;

        URL popularUrl=null;
        URL recentUrl=null;
        
        BufferedReader br=null;

        try {
            popularUrl = new URL(popularUrlString);
            recentUrl = new URL(recentUrlString);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AprendizDelicious.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            br = new BufferedReader(new InputStreamReader(popularUrl.openStream()));
        } catch (IOException ex) {
            Logger.getLogger(AprendizDelicious.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(br.readLine());
        } catch (IOException ex) {
            Logger.getLogger(AprendizDelicious.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            br = new BufferedReader(new InputStreamReader(recentUrl.openStream()));
        } catch (IOException ex) {
            Logger.getLogger(AprendizDelicious.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(br.readLine());
        } catch (IOException ex) {
            Logger.getLogger(AprendizDelicious.class.getName()).log(Level.SEVERE, null, ex);
        }

        //TODO: format info from delicious

        String info="I got something from delicious";

        sendMessage(message.createReply("inform", info));

    }

}
