/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.users;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Bastien
 */
public class UserList extends DefaultListModel<User>{
    
    public UserList() throws UnknownHostException {
        super();
        //this.addUser(new User("You", InetAddress.getLocalHost()));
    }
    
    public boolean isAlreadyConnected(InetAddress address){
        for(int n =0; n<this.size(); n++){
            if(this.get(n).getAddress().equals(address)){
                return true;
            }
        }
        return false;
    }
    
    public void addUser(User user){
        this.addElement(user);
        System.out.println("NEW USER : "+this.size() + "  " + this);
    }
    
    public void delete(){
        this.removeAllElements();
        System.out.println("Delete , stays : " + this.size() + "  "+this);
    }
    
    public void removeUser(User user){
        this.removeElement(user);
        System.out.println("Delete , stays : ");
        for(int n =0; n<this.size(); n++){
            System.out.println(this.get(n));
        }
    }
    
    public User searchUser(String nickname){
        for(int n =0; n<this.size(); n++){
            if(this.get(n).getNickname() == nickname){
                return this.get(n);
            }
        }
        return null;
    }
    
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
