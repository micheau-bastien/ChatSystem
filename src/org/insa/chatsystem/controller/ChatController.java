/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.insa.chatsystem.ni.*;
import org.insa.chatsystem.messages.*;
import org.insa.chatsystem.users.*;
import org.insa.chatsystem.gui.*;

/**
 *
 * @author Bastien
 */
public class ChatController implements NItoController, GuiToController{
    private ChatNI chatNI;
    private ChatControllerToChatNI chatControllerToChatNI;
    private User localUser;
    private UserList connectedUserList;
    private MessageObserver messageObserver;
    
    public ChatController(GUI gui) throws SocketException, UnknownHostException {
        this.messageObserver = gui;
        this.connectedUserList = new UserList();
        this.chatNI = new ChatNI();
        this.chatControllerToChatNI = this.chatNI;
        start();
    }
    
    private void start() {
        this.chatNI.setNiToController(this);
    }

    public UserList getUserList(){
        return this.connectedUserList;
    }
    
    @Override
    public synchronized void rcvMessage(InetAddress source, Message message) throws IOException {
        if(!source.equals(InetAddress.getLocalHost())){
            switch (message.getType()){
                case Message.TYPE_HELLO : /*HELLO*/
                    System.out.println("HELLO RECU");
                    if(!this.connectedUserList.isAlreadyConnected(source)){
                        // Ajouter l'expéditeur à la liste des users
                        this.connectedUserList.addUser(new User(((MessageHello)message).getNickname(), source));
                        if(((MessageHello)message).isReqReply()){
                            System.out.println("HELLO RENVOYE");
                            synchronized(this.chatControllerToChatNI){
                                System.out.println("Renvoi de hello, chatControllertoNI : "+this.chatControllerToChatNI + " from : "+source +" localu : "+this.localUser);
                                chatControllerToChatNI.sendMessage(source, new MessageHello(this.localUser.getNickname(), false));
                            }
                        }
                    }else{
                        // @TODO : LAure
                        System.out.println("DEJA CONNECTE");
                    }
                    break;
                case Message.TYPE_BYE : /*BYE*/
                    // sortir le gars de la liste des users
                    System.out.println("BYEFROM : "+source.getAddress());
                    synchronized(this.connectedUserList){
                        this.connectedUserList.removeUser(this.connectedUserList.searchUser(source));
                    }
                    break;
                case Message.TYPE_MESSAGE : /*MESSAGE*/
                    MessageList.addToMessageDB(((MessageMessage)message), source, InetAddress.getLocalHost());
                    //this.connectedUserList.searchUser(source).addNewUnreadMessage();
                    if (this.connectedUserList.searchUser(source) != null){
                        messageObserver.newMessage(connectedUserList.searchUser(source), ((MessageMessage)message).getMessage()); 
                    }
                    break;
                case Message.TYPE_FILEREQ : /*FILEREQ*/
                    // A gérer 
                    break;
                case Message.TYPE_FILEREQRESP : /*REQRESP*/
                    // Envoyer fichier 
                    break;
                default : 
                    // @TODO : mettre une erreur type message
                    message = null;
                    break;
            }
        }else{
            System.out.println("selfMessage : " +message);
        }
    }

    @Override
    public synchronized void connect(String nickname) throws IOException {
        //AddUser
        System.out.println("Ajout de localuser : "+nickname);
        this.localUser = new User(nickname, InetAddress.getLocalHost());
        this.connectedUserList.addUser(this.localUser);
        this.chatControllerToChatNI.startListening();
        synchronized(this.chatControllerToChatNI){
            chatControllerToChatNI.sendMessage(InetAddress.getByName("255.255.255.255"), new MessageHello(this.localUser.getNickname(), true));
        }
    }
    
    @Override
    public synchronized void logout() throws UnknownHostException, IOException {
        System.out.println("logout");
        synchronized(this.chatControllerToChatNI){
            //chatControllerToChatNI.sendMessage(InetAddress.getByName("255.255.255.255"), new MessageBye());
            chatControllerToChatNI.sendMessage(this.connectedUserList.get(0).getAddress(), new MessageBye());
        }
        this.connectedUserList = new UserList();
        this.localUser = null;
    }

    @Override
    public synchronized void sendMessage(String message, InetAddress dest) throws IOException {
        System.out.println("SENDING MESSAGE TO  : "+dest);
        MessageList.addToMessageDB(new MessageMessage(message), InetAddress.getLocalHost(), dest);
        synchronized(this.chatControllerToChatNI){
            chatControllerToChatNI.sendMessage(dest, new MessageMessage(message));
        }
    }

    @Override
    public void resetUnreadMessages(User user) {
        System.out.println("ResetMessage ConnectedUL : "+this.connectedUserList + " user : "+user);
        synchronized(this.connectedUserList){
            //this.connectedUserList.searchUser(user.getNickname()).resetUnreadMessages();
        }
    }
}
