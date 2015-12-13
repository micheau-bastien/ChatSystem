/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.users;

import java.net.InetAddress;


/**
 * Represent the User object.
 * @author Bastien
 */
public class User {
    private String nickname;
    private InetAddress address;
    private int nbUnreadMessages = 0;
    
    /**
     * Create the User object with his nickname and his IP address.
     * @param nickname
     * @param address IP
     */
    public User(String nickname, InetAddress address){
        this.nickname = nickname;
        this.address = address;
    }

    /**
     * @return the user's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return the user's address
     */
    public InetAddress getAddress() {
        return address;
    }
    
    @Override
    public String toString(){
        if (this.nbUnreadMessages == 0){
            return (this.getNickname()+": "+this.getAddress().getHostAddress());
        } else {
            return ("["+this.nbUnreadMessages+"] "+this.getNickname()+": "+this.getAddress().getHostAddress());
        }
    }

    /**
     * Add a new unread message from this user.
     */
    public void addNewUnreadMessage(){
        this.nbUnreadMessages++;
    }
    
    /**
     * Reset the unread messages counter received from this user.
     */
    public void resetUnreadMessages(){
        this.nbUnreadMessages = 0;
    }
}
