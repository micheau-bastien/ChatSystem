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
 *
 * @author Bastien
 */
public interface UDPReceiverToChatNI {
    void rcvdMessage(InetAddress source, Message message) throws IOException;
}
