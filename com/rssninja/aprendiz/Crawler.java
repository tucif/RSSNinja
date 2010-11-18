package com.rssninja.aprendiz;

import jadex.runtime.*;
import org.htmlparser.beans.StringBean;

/**
 *
 * @author rsantos
 */
public class Crawler extends Plan { 

    public Crawler() {
        System.out.println("Crawler creado");
    }

    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String content = (String) message.getContent();
        System.out.println(content);        

        StringBean sb = new StringBean();
        sb.setLinks(false);
        sb.setReplaceNonBreakingSpaces(true);
        sb.setCollapse(true);        
        sb.setURL(content);
        String s = sb.getStrings();
        if(!(s == null || s.indexOf("org.htmlparser.util.ParserException")!= -1)){
            System.out.println(s);
            sendMessage(message.createReply("inform", "OK"));
        }else{
            System.out.println("empty response");
            sendMessage(message.createReply("inform", "Empty response"));
        }
        
        

    }
}
