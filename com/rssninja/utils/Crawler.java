/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.utils;

import org.htmlparser.beans.StringBean;

/**
 *
 * @author rsantos
 */
public class Crawler {

    public static String Crawl(String url){
        StringBean sb = new StringBean();
        sb.setLinks(false);
        sb.setReplaceNonBreakingSpaces(true);
        sb.setCollapse(true);
        sb.setURL(url);
        String s = sb.getStrings();
        if(!(s == null || s.indexOf("org.htmlparser.util.ParserException")!= -1)){
            return s;            
        }else{
            return "empty response";
        }
    }
}
