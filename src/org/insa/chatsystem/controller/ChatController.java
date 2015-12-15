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
 * This class handle the logic behind the ChatSystem and is the connection between the model, the NI and the view.
 * @author Bastien
 */
public class ChatController implements NItoController, GuiToController{
    private ChatControllerToChatNI chatControllerToChatNI;
    private User localUser;
    private final UserList connectedUserList;
    private final MessageObserver messageObserver;
    
    private boolean isConnected;
    
    /**
     *
     * @param gui
     * @throws SocketException
     * @throws UnknownHostException
     */
    public ChatController(GUI gui) throws SocketException, UnknownHostException {
        this.isConnected = false;
        this.messageObserver = gui;
        this.connectedUserList = new UserList();
        this.chatControllerToChatNI = new ChatNI(this);
    }

    /**
     * Returns te connected user list
     * @return the connected user list object
     */
    public UserList getUserList(){
        return this.connectedUserList;
    }
    
    /**
     * Handle the logic behind a message's reception.
     * @param source : The sender of the received message
     * @param message : The message object received
     * @throws IOException
     * @throws UnknownHostException
     */
    @Override
    public synchronized void rcvMessage(InetAddress source, Message message) throws IOException, UnknownHostException {
        if(this.isConnected == true){
            if(!source.equals(InetAddress.getLocalHost())){
                switch (message.getType()){
                    case Message.TYPE_HELLO : /*HELLO*/
                        this.messageHelloRcvd(source, (MessageHello)message);
                        break;
                    case Message.TYPE_BYE : /*BYE*/
                        this.messageByeRcvd(source, (MessageBye)message);
                        break;
                    case Message.TYPE_MESSAGE : /*MESSAGE*/
                        this.messageMessageRcvd(source, (MessageMessage)message);
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
                System.out.println("selfMessage ! ");
            }
        }else{
            System.out.println("Message reçu alors qu'on est pas connecté de : "+source.getHostAddress());
        }
    }
    
    /**
     * Handle the logic behind a hello message's reception : Add to the source to the conected user list and send back hello if reqReply.
     * @param source : The sender of the received message
     * @param message : The hello message object received
     * @throws IOException
     */
    private void messageHelloRcvd(InetAddress source, MessageHello message) throws IOException{
        System.out.println("HELLO RECU");
        if(!this.connectedUserList.isAlreadyConnected(source)){
            // Ajouter l'expéditeur à la liste des users
            this.connectedUserList.addUser(new User(message.getNickname(), source));
        }else{
            // @TODO : LAure
            System.out.println("DEJA CONNECTE");
        }
        if(message.isReqReply()){
            System.out.println("HELLO RENVOYE");
            synchronized(this.chatControllerToChatNI){
                System.out.println("Renvoi de hello, chatControllertoNI : "+this.chatControllerToChatNI + " from : "+source +" localu : "+this.localUser);
                chatControllerToChatNI.sendMessage(source, new MessageHello(this.localUser.getNickname(), false));
            }
        }
    }
    
    /**
     * Handle the logic behind a bye message's reception : Remove the sender from the connected user list.
     * @param source : The sender of the received message
     * @param message : The bye message object received
     */
    private void messageByeRcvd(InetAddress source, MessageBye message){
        // sortir le gars de la liste des users
        System.out.println("BYEFROM : "+source.getAddress());
        synchronized(this.connectedUserList){
            this.connectedUserList.removeUser(this.connectedUserList.searchUser(source));
        }
    }
    
    /**
     * Handle the logic behind a message's reception : Add the message to the database and send it to the GUI.
     * @param source : The sender of the received message
     * @param message : The message object received
     */
    private void messageMessageRcvd(InetAddress source, MessageMessage message) throws UnknownHostException{
        MessageList.addToMessageDB(message, source, InetAddress.getLocalHost());
        if (this.connectedUserList.searchUser(source) != null){
            this.connectedUserList.searchUser(source).addNewUnreadMessage();
            messageObserver.newMessage(connectedUserList.searchUser(source), message.getMessage()); 
        }
    }
    
    
    /**
     * Handle the localUser connection, will be executed when the connect button from the GUIConnection is clicked.
     * @param nickname
     * @throws IOException
     */
    @Override
    public void connect(String nickname) throws IOException {
        System.out.println("Ajout de localuser : "+nickname);
        this.localUser = new User(nickname, InetAddress.getLocalHost());
        this.connectedUserList.addUser(this.localUser);
        this.connectedUserList.addUser(new User("All", InetAddress.getByName("255.255.255.255")));
        this.chatControllerToChatNI.startListening();
        synchronized(this.chatControllerToChatNI){
            chatControllerToChatNI.sendMessage(InetAddress.getByName("255.255.255.255"), new MessageHello(this.localUser.getNickname(), true));
        }
        this.isConnected = true;
    }
    
    /**
     * Handle the local user logout, will be executed when the logout button from the GUIConnected view is clicked.
     * @throws UnknownHostException
     * @throws IOException
     */
    @Override
    public synchronized void logout() throws UnknownHostException, IOException {
        System.out.println("logout");
        synchronized(this.chatControllerToChatNI){
            chatControllerToChatNI.sendMessage(InetAddress.getByName("255.255.255.255"), new MessageBye());
        }
        this.isConnected = false;
        this.localUser = null;
        this.connectedUserList.removeAllUsers();
    }

    /**
     * Send the message passed by the GUIConected to the destination user when the send button is clicked.
     * @param destUser
     * @param message
     * @throws IOException
     */
    @Override
    public synchronized void sendMessage(User destUser, String message) throws IOException {
        System.out.println("SENDING MESSAGE TO  : "+destUser.getAddress());
        synchronized(this.chatControllerToChatNI){
            chatControllerToChatNI.sendMessage(destUser.getAddress(), new MessageMessage(message));
        }
        MessageList.addToMessageDB(new MessageMessage(message), InetAddress.getLocalHost(), destUser.getAddress());
    }
}
