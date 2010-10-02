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

        String s="[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
        Object obj = JSONValue.parse(content);
        JSONArray array = (JSONArray) obj;
        System.out.println("======the 2nd element of array======");
        System.out.println(array.get(1));
        System.out.println();

    }
}
