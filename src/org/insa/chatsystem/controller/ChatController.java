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
    private ControllerToGUI controllerToGUI;
    private User localUser;
    private UserList connectedUserList;
    private MessageObserver messageObserver;
    
    public ChatController(GUI gui) throws SocketException, UnknownHostException {
        messageObserver = gui.getGuiConnected();
        this.controllerToGUI = gui;
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
    public void rcvMessage(InetAddress source, Message message) throws IOException {
        System.out.println(message.getType());
        if(!source.equals(InetAddress.getLocalHost())){
            switch (message.getType()){
                case Message.TYPE_HELLO : /*HELLO*/
                    System.out.println("HELLO RECU");
                    // Ajouter l'expéditeur à la liste des users
                    this.connectedUserList.addUser(new User(((MessageHello)message).getNickname(), source));
                    if(((MessageHello)message).isReqReply()){
                        System.out.println("HELLO RENVOYE");
                        chatControllerToChatNI.sendMessage(source, new MessageHello(this.localUser.getNickname(), false));
                    }
                    break;
                case Message.TYPE_BYE : /*BYE*/
                    // sortir le gars de la liste des users
                    System.out.println("BYEFROM : "+source.getAddress());
                    this.connectedUserList.removeUser(this.connectedUserList.searchUser(source));
                    break;
                case Message.TYPE_MESSAGE : /*MESSAGE*/
                    MessageList.addToMessageDB(((MessageMessage)message), source, InetAddress.getLocalHost());
                    messageObserver.newMessage(connectedUserList.searchUser(source), ((MessageMessage)message).getMessage());
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
    public void connect(String nickname) throws IOException {
        System.out.println(nickname);
        //AddUser
        this.localUser = new User(nickname, InetAddress.getLocalHost());
        this.connectedUserList.addUser(this.localUser);
        chatControllerToChatNI.sendMessage(InetAddress.getByName("255.255.255.255"), new MessageHello(this.localUser.getNickname(), true));
    }
    
    @Override
    public void logout() throws UnknownHostException, IOException {
        System.out.println("logout");
        chatControllerToChatNI.sendMessage(InetAddress.getByName("255.255.255.255"), new MessageBye());
        this.connectedUserList = new UserList();
        this.localUser = null;
    }

    @Override
    public void sendMessage(String message, InetAddress dest) throws IOException {
        System.out.println("SENDING MESSAGE TO  : "+dest);
        MessageList.addToMessageDB(new MessageMessage(message), InetAddress.getLocalHost(), dest);
        chatControllerToChatNI.sendMessage(dest, new MessageMessage(message));
    }
}
