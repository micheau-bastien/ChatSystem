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
public class UserList{
    private ArrayList<User> userList;
    
    public UserList() throws UnknownHostException {
        this.userList = new ArrayList<User>();
        this.userList.add(new User("All", InetAddress.getByName("255.255.255.255")));
    }
    
    public void addUser(User user){
        this.getUserList().add(user);
    }
    
    public void removeUser(User user){
        this.getUserList().remove(user);
    }
    
    public User searchUser(String nickname){
        int n=0;
        while(this.getUserList().get(n).getNickname() != nickname && n <= this.getUserList().size()){
            n++;
        }
        if (n > this.getUserList().size()){
            // @TODO : NullPointerException
            return null;
        }else{
            return this.getUserList().get(n);
        }
    }
    
    public User searchUser(InetAddress address){
        int n=0;
        while(this.getUserList().get(n).getAddress()!= address && n <= this.getUserList().size()){
            n++;
        }
        if (n > this.getUserList().size()){
            // @TODO : NullPointerException
            return null;
        }else{
            return this.getUserList().get(n);
        }
    }

    /**
     * @return the userList
     */
    public ArrayList<User> getUserList() {
        return userList;
    }
    
}
