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
        
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        return json;
    }
}
