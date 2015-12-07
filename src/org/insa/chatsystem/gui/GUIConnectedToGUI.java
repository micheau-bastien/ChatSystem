/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.io.IOException;
import java.net.InetAddress;
import org.insa.chatsystem.users.UserList;

/**
 *
 * @author laure
 */
public interface GUIConnectedToGUI {
    public void send(String text, InetAddress destination) throws IOException;
    public void logout() throws IOException ;
    public UserList fetchUserList();
    
}
