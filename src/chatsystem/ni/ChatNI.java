/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem.ni;

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
    
    public ChatNI(){
        this.udpSender = new UDPSender();
        this.udpReceiver = new UDPReceiver();
        this.udpReceiver.setChatNI(this);
        this.tcpServer = new TCPServer();
        
    }
}
