package com.rssninja;
//import java.util.*;

import jadex.runtime.*;
import org.json.simple.*;
import jadex.adapter.fipa.SFipa;


public class AprendizTwitter extends Plan {

    public AprendizTwitter() {
        System.out.println("AprendizTwitter creado");
    }

    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();

        System.out.println("service=twitter");
        System.out.println("tag="+tag);
        System.out.println();

        //TODO: search twitter for tag

        //TODO: parse twitter info divided by tweets

        String info="I got something from twitter";

        sendMessage(message.createReply("inform", info));

    }

}
