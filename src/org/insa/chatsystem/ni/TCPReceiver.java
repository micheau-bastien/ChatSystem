/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author Bastien
 */
public class TCPReceiver extends Thread {
    private InputStreamReader input;
    private BufferedReader reader;
    private BufferedWriter writer;
    private final Socket socket;
    private OutputStream output;
    
    public TCPReceiver (Socket socket, OutputStream output){
        this.socket = socket;
        this.output = output;
    }
    
    @Override
    public void run(){
        //On attend la connection en acceptant, quand la connection est établie on a un socket
        try {
            // Le socket nous fourni les moyens de lecture et d'écriture
            input = new InputStreamReader(this.socket.getInputStream());
            DataInputStream datain = new DataInputStream(this.socket.getInputStream());
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = datain.read(buffer)) != -1){
                output.write(buffer, 0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
