/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.net.DatagramSocket;

/**
 *
 * @author Bastien
 */
public class ChatNI {
    public static final int PORT = 8045;
    public static final int TYPE_HELLO = 0;
    public static final int TYPE_BYE = 1;
    public static final int TYPE_MESSAGE = 2;
    
    private UDPSender udpSender;
    private UDPReceiver udpReceiver;
    private TCPServer tcpServer;
    private TCPReceiver tcpReceiver;
    private TCPSender tcpSender;
    private DatagramSocket socket;
    
    public ChatNI(){
        try {
            socket = new DatagramSocket(ChatNI.PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.udpSender = new UDPSender(socket);
        this.udpReceiver = UDPReceiver.sharedInstance(socket);
        this.udpReceiver.setChatNI(this);
        this.tcpServer = new TCPServer();
        
    }

    /**
     * @return the udpSender
     */
    public UDPSender getUdpSender() {
        return udpSender;
    }

    /**
     * @return the udpReceiver
     */
    public UDPReceiver getUdpReceiver() {
        return udpReceiver;
    }
}
