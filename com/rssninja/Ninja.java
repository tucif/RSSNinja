/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja;

import jadex.runtime.*;
import jadex.adapter.fipa.SFipa;

/**
 *
 * @author unicorn
 */
public class Ninja extends Plan{
    public Ninja() {
        System.out.println("Ninja creado");
    }
    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String content = (String) message.getContent();
        System.out.println(content);
        int count = Integer.parseInt(content);
        while(count>0){
            sendMessage(message.createReply(SFipa.INFORM, "OK"));
            count --;
          /*  Aprendiz a=new Aprendiz();
            a.body();
            IMessageEvent ime = createMessageEvent("");
            ime.setContent("shalalala");
            ime.getParameterSet(SFipa.RECEIVERS).addValue(a.getAgentIdentifier());
            sendMessage(ime);*/
            IGoal ca = createGoal("ams_create_agent");
            
        }
    }

}
