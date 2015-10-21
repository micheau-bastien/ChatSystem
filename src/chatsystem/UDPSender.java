/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import java.net.*;
import java.nio.charset.Charset;
import org.json.*;

/**
 *
 * @author Bastien
 */
public class UDPSender {    
    int port = 8045;
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] buf;
    
    public UDPSender(){
        try {
            socket = new DatagramSocket(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // TODO : Faire fonction String2INET
    private void sendJSON(InetAddress address, JSONObject json){
        try {
            buf = json.toString().getBytes(Charset.forName("UTF-8"));
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet); 
        } catch (Exception e) {
            System.out.println("ici ! ");
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void sendJSON(String address, JSONObject json){
        System.out.println("1");
        try {
            this.sendJSON(InetAddress.getByName(address), json);
        } catch (Exception e) {
            System.out.println("e1");
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    public void sendHello(){
        JSONObject hello = new JSONObject();
        hello.put("type", new Integer(0));
        hello.put("nickname", new String("Bast"));
        hello.put("reqReply", new Boolean(true));
        
        this.sendJSON("255.255.255.255" ,hello);
    }
    
    public void sendHelloBack(InetAddress address){
        JSONObject hello = new JSONObject();
        hello.put("type", new Integer(0));
        hello.put("nickname", new String("Bast"));
        hello.put("reqReply", new Boolean(false));
        
        this.sendJSON(address ,hello);
    }
    
    public void sendBye(){
        JSONObject bye = new JSONObject();
        bye.put("type", new Integer(1));
        bye.put("nickname", new String("Bast"));
        
        this.sendJSON("255.255.255.255", bye);
    }
    
    public void sendMessage(String address, String s){
        JSONObject message = new JSONObject();
        message.put("type", new Integer(2));
        message.put("nickname", new String("Bast"));
        message.put("message", new String(s));
        this.sendJSON(address, message);
    }
    
    public static void main(String[] args) throws Exception {
        UDPSender sender = new UDPSender();
        sender.sendHello();
    }
}
