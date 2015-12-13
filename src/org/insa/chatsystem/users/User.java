/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.users;

import java.net.InetAddress;


/**
 *
 * @author Bastien
 */
public class User {
    private String nickname;
    private InetAddress address;
    private int nbUnreadMessages = 0;
    
    /**
     *
     * @param nickname
     * @param address
     */
    public User(String nickname, InetAddress address){
        this.nickname = nickname;
        this.address = address;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return the address
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
     *
     */
    public void addNewUnreadMessage(){
        this.nbUnreadMessages++;
    }
    
    /**
     *
     */
    public void resetUnreadMessages(){
        this.nbUnreadMessages = 0;
    }
}
