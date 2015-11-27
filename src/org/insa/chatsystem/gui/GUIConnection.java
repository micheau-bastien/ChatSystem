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

/**
 *
 * @author Bastien
 */
public class GUIConnection extends GUI{
    public GUIConnection(){
        JTextField nicknameTextField = new JTextField("nickname");
        this.getContentPane().add(nicknameTextField);
        
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("");
            }
        });
        this.getContentPane().add(connectButton);
    }
}
