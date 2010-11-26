/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author rsantos
 */
public class SemanticAnalizer {
    
    public static synchronized HashMap<String,Integer> Analize(String text){
        String [] words = text.split(" ");
        HashMap<String,Integer> tagCloud = new HashMap<String, Integer>();
        
        int max = 0;
        for(int i=0; i< words.length; i++){
            String w = words[i].trim();
            if(isPreposition(w)){ //w.isEmpty() || 
                continue;
            }

            if(tagCloud.containsKey(w)){
                int count = tagCloud.get(w)+1;
                tagCloud.put(w, count);
                if(count > max){
                    max = count;
                }
            }
            else{
                tagCloud.put(w, 1);
            }
        }
        
        ArrayList<String> relatedWords = new ArrayList<String>();
        int threshold = 1 + max/10;
        relatedWords.addAll(tagCloud.keySet());
        for(String related : relatedWords){
            if(tagCloud.get(related) < threshold){         
                System.out.println("NOT RELATED:  ("+tagCloud.get(related)+"/"+max+":"+threshold+") - "+related);
                tagCloud.remove(related);
            }
        }       
        return tagCloud;
        
    }

    public static boolean isPreposition(String related){
        return Collections.binarySearch(prepositions, related)!= -1;
    }


    private static final String [] prepos = {
        //"&",
        //"-",
        //"/",
        "a",
        "an",
        "aboard",
        "about",
        "above",
        "absent",
        "across",
        "after",
        "against",
        "along",
        "alongside",
        "amid",
        "amidst",
        "among",
        "amongst",
        "around",
        "as",
        "aside",
        "astride",
        "at",
        "athwart",
        "atop",
        "barring",
        "before",
        "behind",
        "below",
        "beneath",
        "beside",
        "besides",
        "between",
        "betwixt",
        "beyond",
        "but",
        "by",
        "circa",
        "concerning",
        "despite",
        "down",
        "during",
        "except",
        "excluding",
        "failing",
        "following",
        "for",
        "from",
        "given",
        "i",
        //"i'm",
        "if",
        "in",
        "including",
        "inside",
        "into",
        "is",
        "like",
        "my",
        "mid",
        "midst",
        "minus",
        "near",
        "next",
        "notwithstanding",
        "of",
        "off",
        "on",
        "onto",
        "opposite",
        "out",
        "outside",
        "over",
        "pace",
        "past",
        "per",
        "plus",
        "pro",
        "qua",
        "regarding",
        "round",
        "save",
        "since",
        "the",
        "than",
        "through",
        "thru",
        "throughout",
        "thruout",
        "till",
        "times",
        "to",
        "toward",
        "towards",
        "under",
        "underneath",
        "unlike",
        "until",
        "up",
        "upon",
        "versus",       
        "via",
        "vice",
        "vs",
        "what",
        "which",
        "with",
        "within",
        "without",
        "worth"
    };
    private static final ArrayList<String> prepositions = new ArrayList<String>(100);
    static{
        prepositions.addAll(Arrays.asList(prepos));
    }
}
