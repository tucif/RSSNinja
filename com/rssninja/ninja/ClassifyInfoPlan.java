/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.ninja;

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
        System.out.println("RECEIVED INFO!!!!!!!!!!!!");
        
    }
}
