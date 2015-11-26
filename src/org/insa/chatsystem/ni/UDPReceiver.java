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
 *
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

    public static UDPReceiver sharedInstance(){
        return INSTANCE;
    }
    
    public static UDPReceiver sharedInstance(DatagramSocket socket){
        UDPReceiver.socket = socket;
        return UDPReceiver.INSTANCE;
    }    
    
    // todo : Bouger dans ChatController
    private void rcvHello(DatagramPacket packet){
        String data = new String(packet.getData(), 0, packet.getLength());
        JSONObject dataJSON = new JSONObject(data);
        if(dataJSON.getBoolean("reqReply") == true){
            UDPSender sender = new UDPSender(this.socket);
            InetAddress adrs = packet.getAddress();
            //sender.sendHelloBack(adrs);
        }else{
            // @TODO : Ajouter le mec à la liste des users
        }
    }

    @Override
    public void run(){
        try {
            while (true) {
                socket.receive(this.packet);
                String data = new String(packet.getData(), 0, packet.getLength());
                System.out.println(data);
                JSONObject dataJSON = new JSONObject(data);
                this.udpReceiverToChatNI.rcvdMessage(packet.getAddress(), Message.fromJSON(dataJSON));
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the socket
     */
    public DatagramSocket getSocket() {
        return socket;
    }

    /**
     * @param udpReceiverToChatNI the udpReceiverToChatNI to set
     */
    public void setUdpReceiverToChatNI(UDPReceiverToChatNI udpReceiverToChatNI) {
        this.udpReceiverToChatNI = udpReceiverToChatNI;
    }
}
