/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.messages;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Bastien
 */
public class MessageMessage extends Message {
    private String message;
    
    /**
     * Build an Text Message object with the text passed in argument.
     * @param message
     */
    public MessageMessage (String message){
        this.message = message;
        this.type = Message.TYPE_MESSAGE;
    }
    
    /**
     * Transform the message in JSON ready to be sent.
     * @return the JSON object from the Message
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("type", new Integer(Message.TYPE_MESSAGE));
        json.put("message", this.getMessage());
        return json;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
}
