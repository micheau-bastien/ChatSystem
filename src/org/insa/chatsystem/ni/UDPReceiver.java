/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.net.*;
import org.insa.chatsystem.messages.Message;
import org.json.*;

/**
 * Receive all the UDP packet to localhost
 * @author Bastien
 */
public class UDPReceiver extends Thread {
    private static DatagramSocket socket;
    private DatagramPacket packet;
    byte[] buf = new byte[256];
    private static UDPReceiver INSTANCE = new UDPReceiver();
    
    private UDPReceiverToChatNI udpReceiverToChatNI;
    
    private UDPReceiver(){
        packet = new DatagramPacket(buf, buf.length); 
    }
    
    /**
     * Get the singleton UDP receiver wich will listen on the socket.
     * @param socket
     * @return The UDP receiver.
     */
    public static UDPReceiver sharedInstance(DatagramSocket socket){
        UDPReceiver.socket = socket;
        return UDPReceiver.INSTANCE;
    }    

    @Override
    public void run(){
        while (true) {
            try {
                socket.receive(this.packet);
                String data = new String(packet.getData(), 0, packet.getLength());
                JSONObject dataJSON = new JSONObject(data);
                System.out.println("Reçu : "+dataJSON+" from: "+packet.getAddress());
                this.udpReceiverToChatNI.rcvdMessage(packet.getAddress(), Message.fromJSON(dataJSON));
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * @param udpReceiverToChatNI the udpReceiverToChatNI to set
     */
    public void setUdpReceiverToChatNI(UDPReceiverToChatNI udpReceiverToChatNI) {
        this.udpReceiverToChatNI = udpReceiverToChatNI;
    }
}
