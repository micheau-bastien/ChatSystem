/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.net.UnknownHostException;
import org.insa.chatsystem.users.User;

/**
 *
 * @author Bastien
 */
public interface GuiToGuiConnected {

    /**
     * Transmit the new message to the connected gui.
     * @param user
     * @param message
     * @throws UnknownHostException
     */
    public void newMessage(User user, String message) throws UnknownHostException;

    /**
     * Reconnect the GUI Connected view by refreshing the list of connected users.
     */
    public void connect();
}
