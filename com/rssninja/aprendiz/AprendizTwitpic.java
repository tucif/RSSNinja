/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rssninja.aprendiz;

import com.rssninja.utils.UrlManager;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author unicorn
 */
public class AprendizTwitpic  extends Plan{
    public AprendizTwitpic(){
        System.out.println("[Tp] Aprendiz Twitpic creado");
        //API Key 88f7a7eee284d6b811d8f1f0621002ab<--- flickr
        //API SECRET c2d41bc887cb511f<---Flickr
    }
    @Override
    public void body() {
        IMessageEvent message = (IMessageEvent) getInitialEvent();
        String tag = (String) message.getContent();
        System.out.println("[Tp] Received tag: "+tag);
        String api = "http://api.twitpic.com/2/tags/show.json?tag="+tag;
        System.out.println(api);
        SendMessageToNinja(getPictures(api), message);
    }
    /*
     * @param Receives a string that is the url
     * return a JSONArray with all the pictures
     */
    public JSONArray getPictures(String url){
        JSONObject response  = null;
        JSONArray photos = null;

        response = UrlManager.getJSONObjFromURL(url);
        //verify if images != null
        if(response.containsKey("images")){
            photos = (JSONArray)response.get("images");
        }

        return photos;

    }
    public void SendMessageToNinja(JSONArray photos,IMessageEvent message){
        System.out.println("Se va a enviar un mensaje");
        JSONObject objectToSend = null;
        JSONObject metadata = null;

        if(photos == null){
            //if you reach here that means that your tag doesn't generate results
            sendMessage(message.createReply("inform","ooops!"));
        }else{
            //We receive a great JSONAarray , thx to twitpic API :D
            for (Object photo : photos) {
                objectToSend = new JSONObject();
                objectToSend.put("service", "twitpic");
                objectToSend.put("link", "http://twitpic.com/"+((JSONObject)photo).get("id"));
                objectToSend.put("content",((JSONObject)photo).get("message"));

                metadata = new JSONObject();
                metadata.put("type","fulltext");
                JSONObject user = (JSONObject)((JSONObject)photo).get("user");
                metadata.put("author",user.get("user_name"));
                metadata.put("time",((JSONObject)photo).get("timestamp"));
                metadata.put("website",user.get("website"));

                objectToSend.put("metadata", metadata);
                //add metadata
                sendMessage(message.createReply("inform",objectToSend.toString()));
                System.out.println("[Tp] Sent info back to Ninja");
            }
        }
    }
}
