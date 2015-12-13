/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import org.json.*;
import org.insa.chatsystem.messages.*;

/**
 *
 * @author Bastien
 */
public class UDPSender {    
    private final DatagramSocket socket;
    private DatagramPacket packet;
    byte[] buf;
    
    /**
     *
     * @param socket
     */
    public UDPSender(DatagramSocket socket) {
        this.socket = socket;
    }
    
    // TODO : Faire fonction String2INET
    private void sendJSON(InetAddress address, JSONObject json) throws IOException {
        this.buf = json.toString().getBytes(Charset.forName("UTF-8"));
        this.packet = new DatagramPacket(buf, buf.length, address, ChatNI.PORT);
        this.socket.send(packet); 
    }

    // TODO : Faire fonction String2INET

    /**
     *
     * @param address
     * @param message
     * @throws IOException
     */
    public void sendMessage(InetAddress address, Message message) throws IOException{
        buf = message.toJSON().toString().getBytes(Charset.forName("UTF-8"));
        packet = new DatagramPacket(buf, buf.length, address, ChatNI.PORT);
        socket.send(packet);
        System.out.println("Send Message" + message.toJSON()+" to: "+address);
    }
    
    /**
     *
     * @param address
     * @param message
     * @throws IOException
     */
    public void sendMessage(String address, Message message) throws IOException{
            if(address == "localhost"){
                this.sendMessage(InetAddress.getLocalHost(), message);
            }else{
                this.sendMessage(InetAddress.getByName(address), message);
            }
    }
    
    /**
     *
     * @param address
     * @param json
     */
    public void sendJSON(String address, JSONObject json){
        try {
            if(address == "localhost"){
                this.sendJSON(InetAddress.getLocalHost(), json);
            }
            this.sendJSON(InetAddress.getByName(address), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
