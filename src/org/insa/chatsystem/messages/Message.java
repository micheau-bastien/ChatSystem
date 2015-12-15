/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.messages;

import org.json.*;

/**
 * Generic class for all the messages exchanged by the ChatSystem.
 * @author Bastien
 */
public abstract class Message {

    /**
     * Constant for the type of hello messages.
     */
    public static final int TYPE_HELLO = 0;

    /**
     * Constant for the type of bye messages.
     */
    public static final int TYPE_BYE = 1;

    /**
     * Constant for the type of text messages.
     */
    public static final int TYPE_MESSAGE = 2;

    /**
     * Constant for the type of filereq messages.
     */
    public static final int TYPE_FILEREQ = 3;

    /**
     * Constant for the type of reqresp messages.
     */
    public static final int TYPE_FILEREQRESP = 4;
    
    /**
     * The type of the message.
     */
    protected int type;
    
    /**
     * Transform the message in JSON ready to be sent.
     * @return The JSON object from the message.
     */
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        return json;
    }
    
    /**
     * Get the type of the message
     * @return the type.
     */
    public int getType(){
        return this.type;
    }
    
    /**
     * Takes a JSON and return the message corresponding.
     * @param json
     * @return The message object from the received JSON.
     */
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
