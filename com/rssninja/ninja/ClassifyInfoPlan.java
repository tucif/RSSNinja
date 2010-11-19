/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.ninja;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.rssninja.utils.SemanticAnalizer;
import java.util.HashMap;

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
        
        AgentIdentifier agent = (AgentIdentifier)param.getValue();
        String messageContent=(String)json.get("content");

        System.out.println("[Ninja] I received a message from : "+agent.getName());
        System.out.println("[Ninja] Message: "+messageContent);
        System.out.println("[Ninja] Semantic Analysis:");
        HashMap<String,Integer> semanticResults = SemanticAnalizer.Analize(messageContent);
        //Hacer conexion a BD y meter
        //words (semanticResults.keys())
        //y cada word con Keyword en Semantic
        //con iteration factor de semanticResults[word]
    }
        
}
