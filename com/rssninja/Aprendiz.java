package com.rssninja;
//import java.util.*;
import jadex.runtime.*;
import org.json.simple.*;

public class Aprendiz extends Plan {

    public Aprendiz() {
        System.out.println("Aprendiz creado");
    }

    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String content = (String) message.getContent();
        System.out.println(content);

        //message format: {"service": "twitter","tag": "teambj"}
        JSONObject obj = (JSONObject)JSONValue.parse(content);
        System.out.println("======parsed input======");
        System.out.println(obj.get("service"));
        System.out.println(obj.get("tag"));
        System.out.println();

        sendMessage(message.createReply("inform", "OK"));


    }
}
