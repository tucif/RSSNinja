package com.rssninja.aprendiz;

import jadex.runtime.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.htmlparser.beans.StringBean;

/**
 *
 * @author rsantos
 */
public class AprendizTwitterSearch extends Plan {

    public AprendizTwitterSearch() {
        System.out.println("Aprendiz twitter search creado");

    }

    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String content = (String) message.getContent();
        System.out.println(content);

        try{
        URL dict = new URL("http://search.twitter.com/search.json?q="+content);
        BufferedReader in = new BufferedReader(new InputStreamReader(dict.openStream()));
        //String inline;
        System.out.println("respuesta recibida");

        JSONObject response = (JSONObject) JSONValue.parse(in);
        JSONArray results = (JSONArray) response.get("results");

        for(int i = 0; i< results.size(); i++){
        System.out.println(((JSONObject)results.get(i)).get("text"));
        System.out.println();
        }

        sendMessage(message.createReply("inform", "OK"));
        //        while ((inline = in.readLine()) != null) {
        //            System.out.println(inline);
        //        }
        //        in.close();
        //        sendMessage(message.createReply("inform", "OK"));
        }catch(Exception e){
        System.out.println(e);
        sendMessage(message.createReply("inform","Could not understand: "+content));
        }      
    }
}
