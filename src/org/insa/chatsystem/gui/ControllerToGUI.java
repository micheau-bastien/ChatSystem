/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.util.ArrayList;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;

/**
 *
 * @author Bastien
 */
public interface ControllerToGUI {
    public void printMessage(String Message, String nicknameExp);
    public ArrayList<User> userList();
    public void user();
}
