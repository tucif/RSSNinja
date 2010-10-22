package com.rssninja.aprendiz;

import com.rssninja.utils.UrlManager;
import jadex.runtime.*;
import org.json.simple.*;

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
        String dict = "http://search.twitter.com/search.json?q="+content;

        System.out.println("respuesta recibida");

        JSONObject response = UrlManager.getJSONObjFromURL(dict);
        JSONArray results = (JSONArray) response.get("results");

        for(int i = 0; i< results.size(); i++){
            System.out.println(((JSONObject)results.get(i)).get("text"));
            System.out.println();
        }
        
        sendMessage(message.createReply("inform", "OK"));
    }
}
