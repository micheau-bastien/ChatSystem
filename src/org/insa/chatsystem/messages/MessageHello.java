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
public class MessageHello extends Message {
    
    private boolean reqReply;
    private String nickname;
    
    public MessageHello (String nickname, boolean reqReply){
        super();
        this.nickname = nickname;
        this.reqReply=reqReply;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("nickname", this.nickname);
        json.put("type", new Integer(Message.TYPE_HELLO));
        json.put("reqReply", reqReply);
        return json;
    }
    
}
