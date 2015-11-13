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
            //Ajouter le mec à la liste des users
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
                switch (dataJSON.getInt("type")){
                    case 0 : /*HELLO*/
                        udpReceiverToChatNI.rcvdHello(packet.getAddress()); 
                        break;
                    case 1 : /*BYE*/
                        udpReceiverToChatNI.rcvdBye(packet.getAddress()); 
                        break;
                    case 2 : /*MESSAGE*/
                        udpReceiverToChatNI.rcvdMessage(packet.getAddress(), dataJSON.getString("message")); 
                        break;
                    case 3 : /*REQFILE*/
                        udpReceiverToChatNI.rcvdFileReq(packet.getAddress(), dataJSON.getString("name")); 
                        break;
                    case 4 : /*REPREQ*/
                        udpReceiverToChatNI.rcvdReqResp(packet.getAddress(), dataJSON.getBoolean("ok")); 
                        break;    
                }
                
                if(dataJSON.getInt("type") == 0){ /* HELLO */
                    udpReceiverToChatNI.rcvdHello(packet.getAddress());
                } else if (dataJSON.getInt("type") == 1){ 
                    this.rcvBye(packet);
                }else{
                    this.rcvMsg(packet);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
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
}
