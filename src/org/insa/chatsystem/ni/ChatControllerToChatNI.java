/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.io.IOException;
import java.net.InetAddress;
import org.insa.chatsystem.messages.Message;

/**
 *
 * @author Bastien
 */
public interface ChatControllerToChatNI {
    
    public void sendMessage(InetAddress destination, Message message) throws IOException;
    public void startListening();
    
}
