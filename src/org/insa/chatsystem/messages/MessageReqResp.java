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
    
    private boolean ok;
    
    public MessageReqResp (boolean ok){
        this.ok = ok;
        this.type = Message.TYPE_FILEREQRESP;
    }
    
    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("type", new Integer(Message.TYPE_FILEREQRESP));
        json.put("ok", this.isOk());
        return json;
    }

    /**
     * @return the ok
     */
    public boolean isOk() {
        return ok;
    }
    
}
