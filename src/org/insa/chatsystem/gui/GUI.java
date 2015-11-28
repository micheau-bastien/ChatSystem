/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import javax.swing.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.insa.chatsystem.controller.*;
/**
 *
 * @author Bastien
 */
public class GUI extends JFrame implements ControllerToGUI, GUIConnectionToGUI {
    private final GuiToController guiToController;
    
    
    public GUI() throws SocketException, UnknownHostException {
        this.guiToController = new ChatController();
        //this.setSize(250, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        //this.setContentPane(new GUIConnection(this));
        //this.draw();
    }
    
    public void draw(){
        this.pack();
        this.setVisible(true);
    }    

    @Override
    public void printMessage(String Message, String nicknameExp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        this.setContentPane(new GUIConnected());
        this.draw();
    }
}