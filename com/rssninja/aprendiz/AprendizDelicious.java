package com.rssninja.aprendiz;

import com.rssninja.utils.UrlManager;
import jadex.runtime.*;
import org.json.simple.*;

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

        JSONArray popularLinks = UrlManager.getJSONArrFromURL(popularUrlString);
        JSONArray recentLinks = UrlManager.getJSONArrFromURL(recentUrlString);

        sendMessagesToNinja(popularLinks, message);
        sendMessagesToNinja(recentLinks,message);
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
            objectToSend.put("content",UrlManager.getContentFromURL(urlString));

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
}
