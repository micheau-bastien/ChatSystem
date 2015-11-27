/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.users;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Bastien
 */
public class UserList {
    private ArrayList<User> userList;
    
    public UserList() throws UnknownHostException {
        this.userList = new ArrayList<User>();
        this.userList.add(new User("All", InetAddress.getByName("255.255.255.255")));
    }
    
    public void addUser(User user){
        this.userList.add(user);
    }
    
    public void removeUser(User user){
        this.userList.remove(user);
    }
    
    public User searchUser(String nickname){
        int n=0;
        while(this.userList.get(n).getNickname() != nickname && n <= this.userList.size()){
            n++;
        }
        if (n > this.userList.size()){
            // @TODO : NullPointerException
            return null;
        }else{
            return this.userList.get(n);
        }
    }
    
    public User searchUser(InetAddress address){
        int n=0;
        while(this.userList.get(n).getAddress()!= address && n <= this.userList.size()){
            n++;
        }
        if (n > this.userList.size()){
            // @TODO : NullPointerException
            return null;
        }else{
            return this.userList.get(n);
        }
    }
    
}
