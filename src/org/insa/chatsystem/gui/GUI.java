/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import org.insa.chatsystem.controller.*;
/**
 *
 * @author Bastien
 */
public class GUI extends JFrame implements ControllerToGUI, GUIConnectionToGUI {
    private GuiToController guiToController;
    private JPanel panel;
    
    
    public GUI() throws SocketException {
        this.guiToController = new ChatController();
        this.setSize(400, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
    }
    
    public void draw(){
        this.setVisible(true);
    }    

    @Override
    public void printMessage(String Message, String nicknameExp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * @return the guiToController
     */
    public GuiToController getGuiToController() {
        return guiToController;
    }

    @Override
    public void connect(String nickname) {
        this.guiToController.connect(nickname);
    }
}