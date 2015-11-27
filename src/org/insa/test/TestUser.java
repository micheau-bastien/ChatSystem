/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.test;

import java.net.InetAddress;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;


/**
 *
 * @author Bastien
 */
public class TestUser {
    public static void main(String[] args){       
        try {
            UserList userList = new UserList();
            User bast = new User("Bast", InetAddress.getLocalHost());
            User titi = new User("Titi", InetAddress.getLocalHost());
            User laure = new User("Laure", InetAddress.getLocalHost());
            System.out.println(laure);
            userList.addUser(laure);
            userList.addUser(bast);
            userList.addUser(titi);
            User searched = userList.searchUser("Bast");
            System.out.println(bast);
            System.out.println(searched);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
