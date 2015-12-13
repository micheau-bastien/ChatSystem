/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.ni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Bastien
 */
public class TCPSender extends Thread {
    private Socket sock;
    private InputStream input;
    private OutputStream output;
    private BufferedReader reader;
    private BufferedWriter writer;
    private int port;
    private InetAddress address;
    
    /**
     *
     * @param address
     * @param port
     * @throws IOException
     */
    public TCPSender(InetAddress address, int port) throws IOException {
        this.address = address;
        this.port = port;
        sock = new Socket(this.address, this.port);
        input = sock.getInputStream();
        output = sock.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new BufferedWriter(new PrintWriter(new DataOutputStream(output)));
    }
}
