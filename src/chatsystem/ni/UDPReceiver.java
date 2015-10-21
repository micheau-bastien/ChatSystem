/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem.ni;

import java.net.*;
import org.json.*;

/**
 *
 * @author Bastien
 */
public class UDPReceiver {
    private ChatNI chatNI;
    private DatagramSocket socket;
    DatagramPacket packet;
    byte[] buf = new byte[256];

    
         
    public UDPReceiver(){
        try {
            packet = new DatagramPacket(buf, buf.length); 
            socket = new DatagramSocket(ChatNI.PORT); 
            while (true) {
                socket.receive(packet);
                String data = new String(packet.getData(), 0, packet.getLength());
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
            System.out.println(e);
        }
    }
    
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
    
    private void rcvBye(DatagramPacket packet){
        //toController : Enlever user de la liste
    }
    
    private void rcvMsg(DatagramPacket packet){
        System.out.println(packet);
    }
    
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
    
}
