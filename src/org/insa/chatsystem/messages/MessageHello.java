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
        this.nickname = nickname;
        this.reqReply=reqReply;
        this.type = Message.TYPE_HELLO;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("nickname", new String(this.getNickname()));
        json.put("type", new Integer(Message.TYPE_HELLO));
        json.put("reqReply", isReqReply());
        return json;
    }

    /**
     * @return the reqReply
     */
    public boolean isReqReply() {
        return reqReply;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }
    
}
