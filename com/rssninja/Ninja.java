/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.*;
import jadex.adapter.fipa.SFipa;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author unicorn
 */
public class Ninja extends Plan{

    private static String[] APPRENTICE_ARRAY={  "Delicious",
                                                "LastFM",
                                                "Twitpic",
                                                "TwitterSearch",
                                                "Wikipedia"};
    public Ninja() {
        System.out.println("Ninja creado");
    }
    
    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String contentStr = (String) message.getContent();
        System.out.println(contentStr);

        if(message.getType().equals("inform")){
            System.out.println("RECEIVED SEARCH!!!!!!******************");
        }

        //Json format: {"search":"tag"}
        JSONObject content = (JSONObject)JSONValue.parse(contentStr);
        if(content.containsKey("search")){
           String tag = (String)content.get("search");
           sendTagToAll(tag);
        }
    }

    private void sendTagToAll(String tag){
        //Create message
        IMessageEvent ime = createMessageEvent("search");
        ime.setContent(tag);

        //Add receivers to message
        for(String name:Ninja.APPRENTICE_ARRAY){
            String path="com.rssninja.aprendiz.Aprendiz"+name;
            IGoal ca = createGoal("ams_create_agent");
            ca.getParameter("type").setValue(path);
            dispatchSubgoalAndWait(ca);
            AgentIdentifier createdAgent = (AgentIdentifier)ca.getParameter("agentidentifier").getValue();
            ime.getParameterSet(SFipa.RECEIVERS).addValue(createdAgent);
        }

        //Send message
        sendMessage(ime);
    }
}
