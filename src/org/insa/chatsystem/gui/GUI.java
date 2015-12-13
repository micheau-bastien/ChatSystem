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
import java.net.SocketException;
import java.net.UnknownHostException;
import org.insa.chatsystem.controller.*;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;
/**
 * The handler of all GUI in the chatSystem, can take two states, connection or connected. This class could and should be singleton.
 * @author Bastien
 */
public class GUI extends JFrame implements MessageObserver, GUIConnectionToGUI, GUIConnectedToGUI {
    private static GuiToController guiToController;
    private static GuiToGuiConnected guiToGUIConnected;
    private final GUIConnected guiConnected;
    private final GUIConnection guiConnection;
    
    /**
     * Build up the GUI.
     * @throws SocketException
     * @throws UnknownHostException
     */
    public GUI() throws SocketException, UnknownHostException {
        GUI.guiToController = new ChatController(this);
        this.guiConnected = new GUIConnected(this);
        GUI.guiToGUIConnected = guiConnected;
        this.guiConnection = new GUIConnection(this);
        this.setLocationRelativeTo(null);
        this.setTitle("ChatSystem MICHEAU BRICARD");
        this.setMinimumSize(new Dimension(600, 400));
        this.setResizable(true);
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
        this.setContentPane(this.guiConnection);
        this.draw();
    }
    
    /**
     * Redraw the GUI.
     */
    public void draw(){
        this.pack();
        this.setVisible(true);
    }    
    
    /**
     * Before the window closure, this function sends a logout message to everyone.
     * @param e
     */
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

    /**
     * Function will connect the local user, called by a click on the connect button from the GUIConnection view.
     * @param nickname
     * @throws IOException
     */
    @Override
    public void connect(String nickname)  throws IOException  {
        System.out.println("On lance la GUI Connected ! ");
        GUI.guiToController.connect(nickname);
        this.setContentPane(this.guiConnected);
        GUI.guiToGUIConnected.connect();
        this.draw();
    }

    /**
     * Fetch the actual connected user list from the Controller.
     * @return the list of connected user.
     */
    @Override
    public UserList fetchUserList() {
        return guiToController.getUserList();
    }

    /**
     * Function will disconnect the local user, called by a click on the logout button from the GUIConnection view.
     * @throws IOException
     */
    @Override
    public void logout() throws IOException {
        this.guiToController.logout();
        this.setContentPane(this.guiConnection);
        this.draw();
    }

    /**
     * Transmit the message to be sent and the destination user from the connected view to the controller.
     * @param destUser
     * @param text
     * @throws IOException
     */
    @Override
    public void sendMessage(User destUser, String text) throws IOException {
        synchronized(this.guiToController){
            guiToController.sendMessage(destUser, text);
        }
    }

    /**
     * Will transmit the received message from the Controller to the Connected GUI to be displayed.
     * @param user
     * @param message
     * @throws UnknownHostException
     */
    @Override
    public void newMessage(User user, String message) throws UnknownHostException {
        guiToGUIConnected.newMessage(user, message);
    }
}