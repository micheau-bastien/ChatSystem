/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.net.SocketException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

/**
 * 
 * @author Bastien
 */
public class GUIConnected extends JPanel implements ActionListener{
    
    public GUIConnected() {
        JSplitPane hPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        hPane.setLeftComponent(new JLabel("coucou"));
        hPane.setRightComponent(new JLabel("hello"));
        
        // setLayout(new BoxLayout (this, BoxLayout.X_AXIS));
        add(hPane);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}