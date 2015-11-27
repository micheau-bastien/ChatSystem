/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.insa.chatsystem.gui.*;
/**
 *
 * @author Bastien
 */
public class TestGUI {
        public static void main(String[] args){       
        //GUI fenetre = new GUI();
        JPanel pan = new JPanel();
        pan.add(new JTextField());
        JButton bouton = new JButton();
        bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("");
            }
        });
    }   
}
