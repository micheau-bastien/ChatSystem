/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import sun.awt.VerticalBagLayout;

/**
 *
 * @author Bastien
 */
public class GUIConnection extends GUI{
    public GUIConnection() throws SocketException{
        JTextField nicknameTextField = new JTextField("nickname");
        this.getContentPane().add(nicknameTextField);
        
        this.setLayout(new VerticalBagLayout());

        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("");
            }
        });
        this.getContentPane().add(connectButton);
    }

    @Override
    public void printMessage(String Message, String nicknameExp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
