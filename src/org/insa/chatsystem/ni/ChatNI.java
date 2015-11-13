/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import org.insa.chatsystem.controller.NItoController;
import org.insa.chatsystem.messages.Message;

/**
 *
 * @author Bastien
 */
public class ChatNI implements UDPReceiverToChatNI, ChatControllerToChatNI{
    public static final int PORT = 8045;
    
    private UDPSender udpSender;
    private UDPReceiver udpReceiver;
    private TCPServer tcpServer;
    private TCPReceiver tcpReceiver;
    private TCPSender tcpSender;
    private DatagramSocket socket;
    
    private NItoController niToController;
    
    public ChatNI() throws SocketException{
        socket = new DatagramSocket(ChatNI.PORT);
        this.udpSender = new UDPSender(this.socket);
        this.udpReceiver = UDPReceiver.sharedInstance(this.socket); // CECI EST UN NEW 
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

    @Override
    public void rcvdHello(InetAddress source) {
        niToController.rcvHello(source);
    }

    @Override
    public void rcvdBye(InetAddress source) {
        niToController.rcvBye(source);
    }

    @Override
    public void rcvdFileReq(InetAddress source, String nameFile) {
        niToController.rcvFileReq(source, nameFile);
    }

    @Override
    public void rcvdMessage(InetAddress source, String message) {
        niToController.rcvMessage(source, message);
    }

    @Override
    public void rcvdReqResp(InetAddress source, boolean ok) {
        niToController.rcvReqResp(source);
    }

    @Override
    public void sendMessage(InetAddress destination, Message message) throws IOException{
        this.udpSender.sendMessage(destination, message);
    }

 
}
