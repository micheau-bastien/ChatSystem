/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.net.UnknownHostException;
import org.insa.chatsystem.users.User;

/**
 * Interface between the GUI and the GUIConnected.
 * @author Bastien
 */
public interface GuiToGuiConnected {

    /**
     * Transmit the new message to the connected gui.
     * @param user The remote user who sent the message.
     * @param message The text message received.
     * @throws UnknownHostException
     */
    public void newMessage(User user, String message) throws UnknownHostException;

    /**
     * Connect the GUI Connected view.
     */
    public void connect();
}
