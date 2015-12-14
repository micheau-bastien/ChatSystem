/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.users;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;

/**
 * List of all the connected users. This class could and should be singleton.
 * @author Bastien
 */
public class UserList extends DefaultListModel<User>{
    
    /**
     * Build the first (and empty) User list model.
     * @throws UnknownHostException
     */
    public UserList(){
        super();
        //this.addUser(new User("You", InetAddress.getLocalHost()));
    }
    
    /**
     * Check if the user is already connected.
     * @param address of the user
     * @return true if the user is already connected or false elsewise.
     */
    public boolean isAlreadyConnected(InetAddress address){
        for(int n =0; n<this.size(); n++){
            if(this.get(n).getAddress().equals(address)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Add user to the connected user list.
     * @param user to be added
     */
    public void addUser(User user){
        this.addElement(user);
        System.out.println("NEW USER : "+this.size() + "  " + this);
    }
    
    /**
     * Empty the list of connected users
     */
    public void removeAllUsers(){
        this.removeAllElements();
        System.out.println("Delete , stays : " + this.size() + "  "+this);
    }
    
    /**
     * Remove the user from the cnnected user list.
     * @param user to be removed
     */
    public void removeUser(User user){
        this.removeElement(user);
        System.out.println("Delete , stays : ");
        for(int n =0; n<this.size(); n++){
            System.out.println(this.get(n));
        }
    }
    
    /**
     * Search an User from the connected user list by his nickname.
     * @param nickname
     * @return The User object of the searched nickname.
     */
    public User searchUser(String nickname){
        for(int n =0; n<this.size(); n++){
            if(this.get(n).getNickname().equals(nickname)){
                return this.get(n);
            }
        }
        return null;
    }
    
    /**
     * Search an User from the connected user list by his address.
     * @param address
     * @return The User object of the searched nickname.
     */
    public User searchUser(InetAddress address){
        for(int n =0; n<this.size(); n++){
            if(this.get(n).getAddress().equals(address)){
                return this.get(n);
            }
        }
        return null;
    }
    
    @Override
    public String toString(){
        String s = new String("User list : ");
        for(int n =0; n<this.size(); n++){
            s = s+"\n"+this.get(n);
        }
        return s;
    }
}
