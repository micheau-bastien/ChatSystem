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
 * Interface from the controller to the NI.
 * @author Bastien
 */
public interface ChatControllerToChatNI {
    
    /**
     * Send the message built by ChatController.
     * @param destination the IP address of the remote destination user.
     * @param message the text message to be sent.
     * @throws IOException
     */
    public void sendMessage(InetAddress destination, Message message) throws IOException;

    /**
     * Start the listening of the UDP receiver.
     */
    public void startListening();
    
}
