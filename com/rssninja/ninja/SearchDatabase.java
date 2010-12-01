/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.ninja;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.*;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.externalaccesswrapper.BeliefbaseWrapper;
import com.rssninja.models.*;
import com.rssninja.utils.Database;
import java.util.Collection;
import java.util.ArrayList;
/**
 *
 * @author unicorn
 */
public class SearchDatabase extends Plan{

    @Override
    public void body() {
        //obtener el tag del usuario
        IBelief tagBelief = getBeliefbase().getBelief("tag");
        String tag = (String)tagBelief.getFact();
        ArrayList<Link> links = (ArrayList)Database.INSTANCE.getNewLinks(tag);
        getBeliefbase().getBelief("new_links").setFact(links);
        getBeliefbase().getBelief("info_updated").setFact(true);
    }
    
}