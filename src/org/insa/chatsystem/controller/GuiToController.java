/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;

/**
 *
 * @author Bastien
 */
public interface GuiToController {
    public void connect(String nickname)  throws IOException ;
    public void sendMessage(String nickname);
    
    public void logout()  throws UnknownHostException, IOException;
    public UserList getUserList();
}
