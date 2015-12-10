/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.insa.chatsystem.controller.*;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;
/**
 *
 * @author Bastien
 */
public class GUI extends JFrame implements MessageObserver, GUIConnectionToGUI, GUIConnectedToGUI {
    private static GuiToController guiToController;
    private static GuiToGuiConnected guiToGUIConnected;
    private final GUIConnected guiConnected;
    
    public GUI() throws SocketException, UnknownHostException {
        GUI.guiToController = new ChatController(this);
        guiConnected = new GUIConnected(this);
        this.setLocationRelativeTo(null);
        this.setTitle("ChatSystem MICHEAU BRICARD");
        this.setMinimumSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    GUI.guiToController.logout();
                    System.out.println("Closing");  
                } catch (Exception e) {
                    e.printStackTrace();
                }
                          
            }
        }));
        this.setResizable(true);
        this.setContentPane(new GUIConnection(this));
        GUI.guiToGUIConnected = guiConnected;
        this.draw();
    }
    
    private static void shutDown(){
        try {
            GUI.guiToController.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
    public void draw(){
        this.pack();
        this.setVisible(true);
    }    
    public void WindowClosed(WindowEvent e) {
        try {
            System.out.println("ON S'ETTEINT");
            this.guiToController.logout();
            e.getWindow().dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    /**
     * @param panel the panel to set
     */
    public void setPanel(JPanel panel) {
        this.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        this.setContentPane(panel);
    }

    /**
     * @return the guiToController
     */
    public GuiToController getGuiToController() {
        return guiToController;
    }

    @Override
    public void connect(String nickname)  throws IOException  {
        this.guiToController.connect(nickname);
        System.out.println("On lance la GUI Connected ! ");
        this.setContentPane(this.guiConnected);
        this.draw();
    }

    @Override
    public UserList fetchUserList() {
        return guiToController.getUserList();
    }

    @Override
    public void logout() throws IOException {
        this.guiToController.logout();
        this.setContentPane(new GUIConnection(this));
        this.draw();        
    }

    @Override
    public void send(String text, InetAddress destination) throws IOException {
        synchronized(this.guiToController){
            guiToController.sendMessage(text, destination);
        }
    }

    @Override
    public void newMessage(User user, String message) throws UnknownHostException {
        guiToGUIConnected.newMessage(user, message);
    }

    @Override
    public void resetUnreadMessages(User u) {
        synchronized(this.guiToController){
            this.guiToController.resetUnreadMessages(u);
        }
    }

}