/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.insa.chatsystem.messages.Message;

/**
 * The interface between the chatNI and the Controller
 * @author Bastien
 */
public interface NItoController {

    /**
     * Handle the logic behind a message's reception.
     * @param source : The sender of the received message
     * @param message : The message object received
     * @throws IOException
     * @throws UnknownHostException
     */
    void rcvMessage(InetAddress source, Message message) throws IOException, UnknownHostException;
}
