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
        messageDB.add(new MessageTextExchanged(message, source, destination, new Date()));
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
        //System.out.println("recherche des messages vers "+dest);
        for(MessageTextExchanged mte : messageDB){
            //System.out.println("Message de : "+mte.getSource()+ " à : " + mte.getDest() + " disant : "+mte.getMessage());
            if (mte.getSource().equals(dest) && !mte.getSource().equals(InetAddress.getLocalHost()) || (dest.equals(InetAddress.getByName("255.255.255.255")))){
                list.add(mte);
                //System.out.println("Received By You : " + mte.getMessage().toJSON());
            } else if (mte.getSource().equals(InetAddress.getLocalHost()) && mte.getDest().equals(dest)){
                //System.out.println("Sent By You : " + mte.getMessage().toJSON() + "to : " + mte.getDest());
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
