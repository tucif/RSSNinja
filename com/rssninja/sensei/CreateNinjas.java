/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.sensei;

import com.rssninja.utils.Database;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IGoal;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author zheroth
 */
public class CreateNinjas extends Plan {

    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String contentStr = (String) message.getContent();

        //Json format: {"search":"tag"}
        JSONObject content = (JSONObject)JSONValue.parse(contentStr);
        if(content.containsKey("search")){
           String tag = (String)content.get("search");
           CreateAndSendToNinja(tag);

           //Check to see if there are related terms
           ArrayList<String>words=(ArrayList<String>) Database.INSTANCE.getRelatedWords(tag, 1);
           if(words!=null && words.size()>0){
               for(int i=0; i<3;i++){
                   if(words.size()>0){
                       System.out.println("[Sensei] Found a word related to "+tag+" -> "+words.get(i));
                       System.out.println("[Sensei] Created a new Ninja to search for "+words.get(i));
                       CreateAndSendToNinja(words.get(i));
                   }else{
                       break;
                   }
               }
           }

        }else if(content.containsKey("similar")){
            String tag = (String)content.get("similar");
            System.out.println("[Sensei] This are similar words to the tag: "+tag);
            ArrayList<String>words=(ArrayList<String>) Database.INSTANCE.getRelatedWords(tag, 1);
            for(String string : words){
                System.out.println(string);
            }
        }else{
           sendMessage(message.createReply("informSend","Could not understand: "+message.getContent()));
        }
    }
    
    private void CreateAndSendToNinja(String tag){

        //Update database with tag
        Database.INSTANCE.insertKeyword(tag);

        //Create message
        HashMap<String,String> objectToSend=null;

        IMessageEvent ime = createMessageEvent("send_request");

        objectToSend=new HashMap<String,String>();
        objectToSend.put("search", tag);
        objectToSend.put("sensei",this.getAgentIdentifier().getName());

        
        String message = JSONObject.toJSONString(objectToSend);

        ime.setContent(message);

        //Add receivers to message
        String path="com.rssninja.ninja.Ninja";
        IGoal ca = createGoal("ams_create_agent");
        ca.getParameter("type").setValue(path);
        dispatchSubgoalAndWait(ca);

        AgentIdentifier createdAgent = (AgentIdentifier)ca.getParameter("agentidentifier").getValue();
        ime.getParameterSet(SFipa.RECEIVERS).addValue(createdAgent);

        //Send message
        sendMessage(ime);

        //Update my belief base
        this.getBeliefbase().getBeliefSet("tags").addFact(tag);
    }
}
