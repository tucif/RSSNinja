package com.rssninja;

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
        System.out.println(s);
        sendMessage(message.createReply("inform", "OK"));

    }
}
