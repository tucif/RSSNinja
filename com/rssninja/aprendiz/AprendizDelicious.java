package com.rssninja.aprendiz;

import com.rssninja.utils.UrlManager;
import jadex.runtime.*;
import java.util.HashMap;
import org.json.simple.*;

public class AprendizDelicious extends Plan {

    public AprendizDelicious() {
        System.out.println("[De] AprendizDelicious creado");
    }

    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();
        System.out.println("[De] Received tag: "+tag);
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
        HashMap<String,String> objectToSend=null;
        HashMap<String,String> metaData = null;

        for(int i=0; i<jsonArray.size(); i++){
            objectToSend=new HashMap<String,String>();
            currentObject= (JSONObject)jsonArray.get(i);

            urlString= currentObject.get("u").toString();

            objectToSend.put("service","delicious");
            objectToSend.put("link",urlString);
            objectToSend.put("content",UrlManager.getContentFromURL(urlString));

            //generate Meta
            metaData=new HashMap<String,String>();

            metaData.put("type","html");
            metaData.put("title",(String)currentObject.get("d"));
            metaData.put("date",(String)currentObject.get("dt"));
            metaData.put("relatedTags",currentObject.get("t").toString());

            String metaDataStr = JSONObject.toJSONString(metaData);
            objectToSend.put("meta",metaDataStr);
            System.out.println("[De] metadata: "+metaDataStr);

            String jsonResult = JSONValue.toJSONString(objectToSend);

            sendMessage(message.createReply("inform",jsonResult));
            System.out.println("[De] Sent info back to Ninja");
        }
    }
}
