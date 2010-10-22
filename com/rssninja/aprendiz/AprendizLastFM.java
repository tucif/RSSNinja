/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.aprendiz;

import com.rssninja.utils.UrlManager;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import org.json.simple.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *
 * @author unicorn
 */
public class AprendizLastFM extends Plan{

    public AprendizLastFM(){
        System.out.println("Aprendiz LastFM creado!");
    }
    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();

        String url = "http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&format=json&&api_key=b25b959554ed76058ac220b7b2e0a026&artist="+tag;
        SendMessagesToNinja(getTopAlbums(url), message);
    }
    public JSONArray getTopAlbums(String source){
        JSONArray albums = null;
        JSONObject json = UrlManager.getJSONObjFromURL(source); //(JSONObject)JSONValue.parse(r);

        if(json.get("error")==null){
            JSONObject topAlbums = (JSONObject)json.get("topalbums");
            if(topAlbums.get("album")==null){
                //Artist but no album
                System.out.println("No tiene album");
                return null;
            }else{
                albums = (JSONArray) topAlbums.get("album");
                return albums;
            }
        }else{
            //No artis with that tag
            System.out.println("no tiene artista");
            return null;
        }
    }
    public void SendMessagesToNinja(JSONArray albums,IMessageEvent message){
        JSONObject album = null;
        JSONObject metadata = null;
        if(albums != null){
            for(Object i : albums){
                album = new JSONObject();
                album.put("service","lastFM");
                album.put("link", (String)((JSONObject)i).get("url"));
                album.put("content",((JSONObject)i).get("name"));
                metadata = new JSONObject();
                metadata.put("type", "fulltext");
                metadata.put("author", ((JSONObject)((JSONObject)i).get("artist")).get("name"));
                metadata.put("date", "");
                album.put("metadata", metadata);
                sendMessage(message.createReply("inform",album.toString()));
            }
        }else{
            /*
             TODO:check the json format when an error ocurrs
             */
            album = new JSONObject();
            album.put("service", "lastFM");
            album.put("link", "none");
            album.put("content", "none");
            metadata = new JSONObject();
            metadata.put("type", "null");
            metadata.put("author", "none");
            metadata.put("date", "10/10/10");
            album.put("metadata", metadata);
            sendMessage(message.createReply("inform",album.toString()));
        }
    }
}
