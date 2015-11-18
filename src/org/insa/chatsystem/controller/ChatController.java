/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.net.InetAddress;
import java.net.SocketException;
import org.insa.chatsystem.ni.*;
import org.insa.chatsystem.messages.*;

/**
 *
 * @author Bastien
 */
public class ChatController implements NItoController{
    private ChatNI chatNI;
    private ChatControllerToChatNI chatControllerToChatNI;
    
    public ChatController() throws SocketException {
        this.chatNI = new ChatNI();
        
    }

    @Override
    public void rcvMessage(InetAddress source, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rcvHello(InetAddress source) {
        try {
        chatControllerToChatNI.sendMessage(source, new MessageHello("bast", false));            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rcvBye(InetAddress source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rcvFileReq(InetAddress source, String nomFichier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rcvReqResp(InetAddress source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
