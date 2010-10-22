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
        System.out.println("[Ts] Aprendiz twitter search creado");
    }

    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();
        System.out.println("[Ts] Received tag: "+tag);
        String dict = "http://search.twitter.com/search.json?q="+tag;

        //System.out.println("respuesta recibida");

        JSONObject response = UrlManager.getJSONObjFromURL(dict);
        JSONArray results = (JSONArray) response.get("results");

        for(int i = 0; i< results.size(); i++){
            System.out.println(((JSONObject)results.get(i)).get("text"));
            System.out.println();
        }
        //TODO: Send messages back to ninja
        //System.out.println("[Ts] Sent info back to Ninja");

        sendMessage(message.createReply("inform", "OK"));
        
    }
}
