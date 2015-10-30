/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.net.*;
import org.json.*;

/**
 *
 * @author Bastien
 */
public class UDPReceiver extends Thread {
    private ChatNI chatNI;
    private static DatagramSocket socket;
    private DatagramPacket packet;
    byte[] buf = new byte[256];
    private static UDPReceiver INSTANCE = new UDPReceiver();
    
    private UDPReceiver(DatagramSocket socket){
        try {
            this.socket = socket;
            packet = new DatagramPacket(buf, buf.length);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static UDPReceiver sharedInstance(){
        return INSTANCE;
    }
    
    public static UDPReceiver sharedInstance(DatagramSocket socket){
        UDPReceiver.setSocket(socket);
        return UDPReceiver.INSTANCE;
    }
    
    // A supprimer petit à petit
    private UDPReceiver(){
        try {
            packet = new DatagramPacket(buf, buf.length); 
            socket = new DatagramSocket(ChatNI.PORT); 
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // todo : Bouger dans ChatController
    private void rcvHello(DatagramPacket packet){
        String data = new String(packet.getData(), 0, packet.getLength());
        JSONObject dataJSON = new JSONObject(data);
        if(dataJSON.getBoolean("reqReply") == true){
            UDPSender sender = new UDPSender();
            InetAddress adrs = packet.getAddress();
            sender.sendHelloBack(adrs);
        }else{
            //Ajouter le mec à la liste des users
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                socket.receive(this.packet);
                String data = new String(packet.getData(), 0, packet.getLength());
                System.out.println(data);
                JSONObject dataJSON = new JSONObject(data);
                if(dataJSON.getInt("type") == 0){
                    this.rcvHello(packet);
                } else if (dataJSON.getInt("type") == 1){
                    this.rcvBye(packet);
                }else{
                    this.rcvMsg(packet);
                }
            } 
        } catch (Exception e) {
        }
        
    }
    
    
    // todo : Bouger dans ChatController
    private void rcvBye(DatagramPacket packet){
        //toController : Enlever user de la liste
    }
    
    // todo : Bouger dans ChatController
    private void rcvMsg(DatagramPacket packet){
        System.out.println(packet);
    }
    
    // A virer
    public static void main(String[] args) {
        
        UDPReceiver receive = new UDPReceiver();
    }

    /**
     * @return the socket
     */
    public DatagramSocket getSocket() {
        return socket;
    }

    /**
     * @param chatNI the chatNI to set
     */
    public void setChatNI(ChatNI chatNI) {
        this.chatNI = chatNI;
    }

    /**
     * @param socket the socket to set
     */
    private static void setSocket(DatagramSocket socket) {
        UDPReceiver.socket = socket;
    }
    
}
