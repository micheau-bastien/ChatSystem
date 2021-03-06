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
public class MessageFileReq extends Message {
    
    private String name;
    
    /**
     * Build the message.
     * @param name
     */
    public MessageFileReq (String name){
        this.type = Message.TYPE_FILEREQ;
        this.name = name;
    }
    
    /**
     * Transform the message in JSON ready to be sent.
     * @return The JSON object from the message
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("type", new Integer(Message.TYPE_FILEREQ));
        json.put("name", this.getName());
        return json;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
}
