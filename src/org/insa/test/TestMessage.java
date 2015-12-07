/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.test;

import java.net.InetAddress;
import org.insa.chatsystem.controller.ChatController;
import org.insa.chatsystem.messages.*;
import org.insa.chatsystem.ni.*;



/**
 *
 * @author Bastien
 */
public class TestMessage {
    public static void main(String[] args) {
        try {
            //ChatController chat = new ChatController();
            
            /*ChatNI chatNI = new ChatNI();
            UDPReceiver receiver = chatNI.getUdpReceiver();
            receiver.setUdpReceiverToChatNI(chatNI);
            receiver.start();*/
            //chatNI.getUdpSender().sendMessage(InetAddress.getLocalHost(), (Message) new MessageHello("bast", true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
