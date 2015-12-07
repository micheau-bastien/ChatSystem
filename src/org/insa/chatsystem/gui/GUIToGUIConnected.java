/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;

/**
 *
 * @author laure
 */
public interface GUIToGUIConnected {
    public void newMessage(User user, String message)  throws UnknownHostException;
}
