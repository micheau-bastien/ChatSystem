/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.test;

import java.net.InetAddress;
import org.insa.chatsystem.messages.*;
import org.insa.chatsystem.ni.*;



/**
 *
 * @author Bastien
 */
public class TestMessage {
    public static void main(String[] args) {
        try {
            ChatNI chatNI = new ChatNI();
            UDPReceiver receiver = chatNI.getUdpReceiver();
            receiver.start();
            chatNI.getUdpSender().sendMessage(InetAddress.getByName("255.255.255.255"), (Message) new MessageHello("bast", true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
