package com.rssninja.aprendiz;

import com.rssninja.utils.UrlManager;
import jadex.runtime.*;
import java.util.HashMap;
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

        HashMap objectToSend=null;
        HashMap metaData=null;
        JSONObject currentTweet=null;
        
        for(int i = 0; i< results.size(); i++){
            System.out.println("[Ts] "+((JSONObject)results.get(i)).get("text"));
            System.out.println();

            objectToSend=new HashMap<String,String>();
            currentTweet= (JSONObject)results.get(i);

            //String tweetUrl=

            //objectToSend.put("service","twitter");
            //objectToSend.put("link",tweetUrl);
            //objectToSend.put("content",tweetGoesHere);

            //generate Meta
            metaData=new HashMap<String,String>();

            metaData.put("type","fulltext");
            //add more info here

            String metaDataStr = JSONObject.toJSONString(metaData);
            objectToSend.put("meta",metaDataStr);

            String jsonResult = JSONValue.toJSONString(objectToSend);

            sendMessage(message.createReply("inform",jsonResult));
            System.out.println("[Ts] Sent info back to Ninja");

        }
    }
}
