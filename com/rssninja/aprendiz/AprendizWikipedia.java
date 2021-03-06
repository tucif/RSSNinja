package com.rssninja.aprendiz;

import com.rssninja.utils.Crawler;
import jadex.runtime.*;
import org.json.simple.*;


public class AprendizWikipedia extends Plan {

    public AprendizWikipedia() {
        System.out.println("[Wk] AprendizWikipedia creado");
    }

    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();
        System.out.println("[Wk] Received tag: "+tag);
        String url="http://en.wikipedia.org/w/index.php?action=render&title="+tag;

        String text = Crawler.Crawl(url);

        JSONObject objectToSend=new JSONObject();
        objectToSend.put("service","wikipedia");
        objectToSend.put("link",url);
        objectToSend.put("content",text);

        //generate Meta
        JSONObject metaData=new JSONObject();
        metaData.put("type","html");
        objectToSend.put("meta",metaData.toString());
        sendMessage(message.createReply("inform",objectToSend.toString()));
        System.out.println("[Wk] Sent info back to Ninja");

    }
}
