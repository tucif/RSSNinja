/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.ninja;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.*;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.externalaccesswrapper.BeliefbaseWrapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
    }
    
}
