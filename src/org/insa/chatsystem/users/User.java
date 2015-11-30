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
        return (this.getNickname()+": "+this.getAddress());
    }
}
