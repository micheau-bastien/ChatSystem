/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Bastien
 */
public class TCPServer extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;
    private ArrayList<TCPReceiver> listReceiver;
    
    /**
     *
     */
    public TCPServer(){
        try {
            this.serverSocket = new ServerSocket(ChatNI.PORT);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        //On attend la connection en acceptant, quand la connection est établie on a un socket
        try {
            while (true){
                this.socket = this.serverSocket.accept();
                FileOutputStream f = new FileOutputStream("test.txt");
                listReceiver.add(new TCPReceiver(this.socket, f));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
