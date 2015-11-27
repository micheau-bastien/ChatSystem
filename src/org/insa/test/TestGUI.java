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
        try {
            GUI fenetre = new GUI();
            fenetre.setPanel(new GUIConnection(fenetre));
            fenetre.draw();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
