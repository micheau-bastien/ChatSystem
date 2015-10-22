/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.test;

import org.insa.chatsystem.*;
import org.insa.chatsystem.ni.ChatNI;

/**
 *
 * @author Bastien
 */
public class Test {
    public static void main(String[] args) {
        ChatNI chatNI = new ChatNI();
        chatNI.getUdpReceiver().start();
        chatNI.getUdpSender().sendHello();
        chatNI.getUdpSender().sendMessage("localhost", "C'est mon message");
        chatNI.getUdpSender().sendBye();
    }
}
