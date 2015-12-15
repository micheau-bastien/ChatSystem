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
 * The list of all the messages exchanged since the program started
 * @author Bastien
 */
public class MessageList extends ArrayList<MessageTextExchanged> {
    private static MessageList messageDB = new MessageList();
    
    private MessageList(){
        super();
    }
    
    /**
     * Add a message to the database.
     * @param message
     * @param source
     * @param destination
     */
    public static void addToMessageDB(MessageMessage message, InetAddress source, InetAddress destination){
        messageDB.add(new MessageTextExchanged(message, source, destination, new Date()));
    }
    
    /**
     * Add a message to the database.
     * @param messageTextExchanged
     */
    public static void addToMessageDB(MessageTextExchanged messageTextExchanged){
        messageDB.add(messageTextExchanged);
    }
    
    /**
     * Gives all the messages between the local user and another user.
     * @param dest
     * @return all the messages between the local user andanother user.
     * @throws UnknownHostException
     */
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
}
