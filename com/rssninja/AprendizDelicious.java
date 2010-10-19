package com.rssninja;
//import java.util.*;

import jadex.runtime.*;
import org.json.simple.*;
import jadex.adapter.fipa.SFipa;


public class AprendizDelicious extends Plan {

    public AprendizDelicious() {
        System.out.println("AprendizDelicious creado");
    }

    @Override
    public void body(){
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();

        System.out.println("service=Delicious");
        System.out.println("tag="+tag);
        System.out.println();

        //TODO: search delicious for tag

        //TODO: format info from delicious

        String info="I got something from delicious";

        sendMessage(message.createReply("inform", info));

    }

}
