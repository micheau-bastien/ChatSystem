/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.messages;

import org.json.JSONObject;

/**
 *
 * @author Bastien
 */
public class MessageReqResp extends Message {
    
    private final boolean ok;
    
    /**
     * Build a Message req resp object.
     * @param ok
     */
    public MessageReqResp (boolean ok){
        this.ok = ok;
        this.type = Message.TYPE_FILEREQRESP;
    }
    
    /**
     * Gives the JSON object from the message.
     * @return The JSON object from the message
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("type", new Integer(Message.TYPE_FILEREQRESP));
        json.put("ok", this.isOk());
        return json;
    }

    /**
     * @return true if the file can be sent or false otherwise.
     */
    public boolean isOk() {
        return ok;
    }
    
}
