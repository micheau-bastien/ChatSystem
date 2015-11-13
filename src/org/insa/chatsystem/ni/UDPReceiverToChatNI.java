/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.net.InetAddress;
import java.net.Socket;
import org.insa.chatsystem.messages.Message;

/**
 *
 * @author Bastien
 */
public interface UDPReceiverToChatNI {
    void rcvdHello(InetAddress source);
    void rcvdBye (InetAddress source);
    void rcvdFileReq(InetAddress source, String nameFile);
    void rcvdMessage(InetAddress source, String message);
    void rcvdReqResp(InetAddress source, boolean ok);
}
