package com.rssninja;
import java.util.*;
import jadex.runtime.*;

public class Aprendiz extends Plan{
	
	public Aprendiz(){
	    System.out.println("Aprendiz creado");
	}
	
	public void body(){
		IMessageEvent message = (IMessageEvent)getInitialEvent();
		String word = (String)message.getContent();
		System.out.println(word);
	}
}

