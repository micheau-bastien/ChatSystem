/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import chatsystem.ni.UDPReceiver;
import chatsystem.ni.UDPSender;
import org.json.*;
/**
 *
 * @author Bastien
 */
public class ChatSystem {
   
    UDPSender udpSender;
    UDPReceiver udpReceiver;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here    
        JSONObject obj = new JSONObject();
        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        System.out.print(obj);  
        
    }
    
}
