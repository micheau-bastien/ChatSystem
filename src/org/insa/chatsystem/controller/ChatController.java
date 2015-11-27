/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import org.insa.chatsystem.ni.*;
import org.insa.chatsystem.messages.*;

/**
 *
 * @author Bastien
 */
public class ChatController implements NItoController, GuiToController{
    private ChatNI chatNI;
    private ChatControllerToChatNI chatControllerToChatNI;
    private String nickname = "Bast"; //@TODO : A DEFINIR
    
    public ChatController() throws SocketException {
        this.chatNI = new ChatNI();
        this.chatControllerToChatNI = this.chatNI;
        start();
    }
    
    private void start() {
        this.chatNI.setNiToController(this);
    }

    public void sendHello() throws IOException {
        this.chatControllerToChatNI.sendMessage(InetAddress.getByName("255.255.255.255"), new MessageHello(this.nickname, true));
    }
    
    @Override
    public void rcvMessage(InetAddress source, Message message) throws IOException {
        
        switch (message.getType()){
            case Message.TYPE_HELLO : /*HELLO*/
                //Ajouter le gars à la liste des utilisateurs
                if(((MessageHello)message).isReqReply()){
                    chatControllerToChatNI.sendMessage(source, new MessageHello(this.nickname, false));
                }
                break;
            case Message.TYPE_BYE : /*BYE*/
                // sortir le gars de la liste des users
                break;
            case Message.TYPE_MESSAGE : /*MESSAGE*/
                // afficher le message à l'user
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
    }

    @Override
    public void connect(String nickname) {
        System.out.println(nickname);
    }

    @Override
    public void sendMessage(String nickname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
