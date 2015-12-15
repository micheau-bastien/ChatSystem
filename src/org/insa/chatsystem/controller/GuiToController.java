/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;

/**
 * The interface between the GUI and the Controller
 * @author Bastien
 */
public interface GuiToController {

    /**
     * Handle the localUser connection, will be executed when the connect button from the GUIConnection is clicked.
     * @param nickname
     * @throws IOException
     */
    public void connect(String nickname)  throws IOException ;

    /**
     * Send the message passed by the GUIConected to the destination user when the send button is clicked.
     * @param destUser
     * @param message
     * @throws IOException
     */
    public void sendMessage(User destUser, String message) throws IOException;

    /**
     * Handle the local user logout, will be executed when the logout button from the GUIConnected view is clicked.
     * @throws UnknownHostException
     * @throws IOException
     */
    public void logout()  throws UnknownHostException, IOException;

    /**
     * Returns te connected user list
     * @return the connected user list object
     */
    public UserList getUserList();
}
