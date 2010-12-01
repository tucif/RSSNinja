/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.sensei;
import jadex.adapter.fipa.*;
import jadex.runtime.*;
import org.json.simple.*;
import com.rssninja.models.*;
import com.rssninja.utils.Database;
import java.util.Collection;
/**
 *
 * @author rsantos
 */
public class ShowLinks extends Plan {

    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        JSONObject json = (JSONObject) JSONValue.parse((String)message.getContent());
        IParameter param = (IParameter) message.getParameter("sender");

        AgentIdentifier agent = (AgentIdentifier)param.getValue();
        String keyword = (String)json.get("results_for");

        Collection<Knowledge> knowledgeList= Database.INSTANCE.getKnowledgeByKeyword(keyword);

        System.out.println(knowledgeList.size()+" Best ranked related links to "+keyword);
        for(Knowledge k:knowledgeList){
            System.out.println(k.getRelevance()+": "+k.getService()+" -> "+k.getLink());
        }

        
    }

}
