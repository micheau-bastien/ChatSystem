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
    
    public MessageBye(){
        this.type = Message.TYPE_BYE;
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("type", new Integer(Message.TYPE_BYE));
        return json;
    }
}
