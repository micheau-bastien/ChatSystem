/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.net.*;

/**
 *
 * @author Bastien
 */
public class TCPServer extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;
    
    public TCPServer(){
        try {
            this.serverSocket = new ServerSocket(ChatNI.PORT);        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
