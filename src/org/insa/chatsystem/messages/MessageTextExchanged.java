/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.messages;

import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author Bastien
 */
public class MessageTextExchanged {
    private MessageMessage message;
    private InetAddress source, dest;
    private Date date;
    
    public MessageTextExchanged(MessageMessage message, InetAddress source, InetAddress dest, Date date){
        this.source = source;
        this.dest = dest;
        this.message = message;
        this.date = date;
    }

    /**
     * @return the message
     */
    public MessageMessage getMessage() {
        return message;
    }

    /**
     * @return the source
     */
    public InetAddress getSource() {
        return source;
    }

    /**
     * @return the dest
     */
    public InetAddress getDest() {
        return dest;
    }
}
