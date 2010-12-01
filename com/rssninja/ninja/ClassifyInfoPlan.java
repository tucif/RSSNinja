/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.ninja;

import com.rssninja.models.*;
import com.rssninja.utils.Database;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.rssninja.utils.SemanticAnalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author unicorn
 */
public class ClassifyInfoPlan extends Plan{

    public ClassifyInfoPlan() {
    }

    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        JSONObject json = (JSONObject) JSONValue.parse((String)message.getContent());
        IParameter param = (IParameter) message.getParameter("sender");

        IBelief tagBelief = getBeliefbase().getBelief("tag");
        String tagStr = (String) tagBelief.getFact();



        Word tagWord = Database.INSTANCE.getWord(tagStr);
        
        AgentIdentifier agent = (AgentIdentifier)param.getValue();
        String messageContent=(String)json.get("content");
        String link = (String) json.get("link");

        System.out.println("[Ninja] I received a message from : "+agent.getName());
        System.out.println("[Ninja] Message: "+messageContent);
        System.out.println("[Ninja] Semantic Analysis:");
        HashMap<String,Integer> semanticResults = SemanticAnalizer.Analize(messageContent, tagStr);

        int totalLinkRelevance = 0;
        //Guardar link en beliefset de new_links
        

        for(String word : semanticResults.keySet()){
            Word relatedWord = Database.INSTANCE.insertWord(word);
            if(relatedWord == null){
                continue;
            }
            int wordRelevance = semanticResults.get(word); 
            Database.INSTANCE.insertSemantic(tagWord,relatedWord,wordRelevance);                        
            //add word relevance to link acummulated relevance
            totalLinkRelevance += wordRelevance;
        }

        
        //Save link to DB and beliefbase
        Link l = Database.INSTANCE.saveLink(link, tagStr);
        l.setRelevance(totalLinkRelevance);
        l.setService(agent.getName());
        getBeliefbase().getBeliefSet("new_links").addFact(l);

        
        
        getBeliefbase().getBelief("info_updated").setFact(true);
    }
        
}
