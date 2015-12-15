/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.net.UnknownHostException;
import org.insa.chatsystem.users.User;

/**
 * Message Observer, will receive a newMessage notification every time a new text message is received by the Controller.
 * @author Bastien
 */
public interface MessageObserver {

    /**
     * Will transmit the received message from the Controller to the Connected GUI to be displayed.
     * @param user The source user who sent the message.
     * @param message The text message received.
     * @throws UnknownHostException
     */
    public void newMessage(User user, String message) throws UnknownHostException;
}
