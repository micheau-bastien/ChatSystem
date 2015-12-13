/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import org.insa.chatsystem.messages.Message;

/**
 * Transmit the UDPReceiver messages to the ChatNI.
 * @author Bastien
 */
public interface UDPReceiverToChatNI {

    /**
     * Handle the reception of the new Message.
     * @param source The address of the message's sender.
     * @param message The message object, can be : Hello|Bye|Message|FileReq|FileReqRep.
     * @throws IOException
     */
    void rcvdMessage(InetAddress source, Message message) throws IOException;
}
