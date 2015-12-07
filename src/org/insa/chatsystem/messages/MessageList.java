/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.messages;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Bastien
 */
public class MessageList extends ArrayList<MessageTextExchanged> {
    private static MessageList messageDB = new MessageList();
    
    private MessageList(){
        super();
    }
    
    public static void addToMessageDB(MessageMessage message, InetAddress source, InetAddress destination){
        System.out.println("Added to DB : " +message);
        messageDB.add(new MessageTextExchanged(message, source, source, new Date()));
    }
    
    public static void addToMessageDB(MessageTextExchanged messageTextExchanged){
        messageDB.add(messageTextExchanged);
    }
    
    public static MessageList from(InetAddress source){
        MessageList list = new MessageList();
        for(MessageTextExchanged mte : messageDB){
            if (mte.getSource().equals(source)){
                list.add(mte);
            }
        }
        return list;
    }
    
    public static MessageList with(InetAddress dest) throws UnknownHostException{
        MessageList list = new MessageList();
        System.out.println("recherche des messages vers "+dest.toString());
        for(MessageTextExchanged mte : messageDB){
            if (mte.getDest().equals(dest)){
                list.add(mte);
            } else if (mte.getSource().equals(InetAddress.getLocalHost())){
                list.add(mte);
            }
        }
        return list;
    }
    
    public static MessageList to(InetAddress dest){
        MessageList list = new MessageList();
        for(MessageTextExchanged mte : messageDB){
            if (mte.getDest().equals(dest)){
                list.add(mte);
            }
        }
        return list;
    }
}
