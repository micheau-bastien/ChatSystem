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
/**
 *
 * @author Bastien
 */
public abstract class GUI extends JFrame {
    
    public GUI() {
        this.setTitle("Ma première fenêtre Java");
        this.setSize(400, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
    }
    
    public void draw(){
        this.setVisible(true);
    }    
}