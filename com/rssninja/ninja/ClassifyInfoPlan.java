/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.ninja;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.model.jibximpl.Agent;
import jadex.runtime.*;

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
        String contentStr = (String) message.getContent();
        IParameter param = (IParameter) message.getParameter("sender");
        AgentIdentifier agent = (AgentIdentifier)param.getValue();
        System.out.println("[Ninja] I received a message from : "+agent.getName());
    }
        
}
