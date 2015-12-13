/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.io.IOException;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;

/**
 *
 * @author laure
 */
public interface GUIConnectedToGUI {

    /**
     * Transmit the message to be sent and the destination user from the connected view to the controller.
     * @param destUser
     * @param text
     * @throws IOException
     */
    public void sendMessage(User destUser, String text) throws IOException;

    /**
     * Transmit the logout signal launched by a click on the logout button.
     * @throws IOException
     */
    public void logout() throws IOException ;

    /**
     * Ask to the GUI to fetch the connected user list
     * @return The list of connected users
     */
    public UserList fetchUserList();    
}
