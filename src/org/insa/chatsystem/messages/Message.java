/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.messages;

import org.json.*;

/**
 *
 * @author Bastien
 */
public abstract class Message {
    public static final int TYPE_HELLO = 0;
    public static final int TYPE_BYE = 1;
    public static final int TYPE_MESSAGE = 2;
    public static final int TYPE_FILEREQ = 3;
    public static final int TYPE_FILEREQRESP = 4;
    
    protected int type;
    
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        return json;
    }
    
    public int getType(){
        return this.type;
    }
    
    public static Message fromJSON(JSONObject json){
        Message message;
        switch (json.getInt("type")){
            case Message.TYPE_HELLO : /*HELLO*/
                message = new MessageHello(json.getString("nickname"), json.getBoolean("reqReply")); 
                break;
            case Message.TYPE_BYE : /*BYE*/
                message = new MessageBye();
                break;
            case Message.TYPE_MESSAGE : /*MESSAGE*/
                message = new MessageMessage(json.getString("message")); 
                break;
            case Message.TYPE_FILEREQ : /*FILEREQ*/
                message = new MessageFileReq(json.getString("name")); 
                break;
            case Message.TYPE_FILEREQRESP : /*REQRESP*/
                message = new MessageReqResp(json.getBoolean("ok")); 
                break;
            default : 
                // @TODO : mettre une erreur type message
                message = null;          
                break;
        }
        return message;
    }
            
}
