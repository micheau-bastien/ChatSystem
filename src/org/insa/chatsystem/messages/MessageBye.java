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
public class MessageBye extends Message {
    
    /**
     * Build a bye message.
     */
    public MessageBye(){
        this.type = Message.TYPE_BYE;
    }
    
    /**
     * Transform the message in JSON ready to be sent.
     * @return The JSON Object from the message.
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("type", new Integer(Message.TYPE_BYE));
        return json;
    }
}
