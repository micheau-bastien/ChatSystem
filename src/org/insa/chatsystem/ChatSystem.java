/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem;

import org.insa.chatsystem.gui.GUI;
/**
 *
 * @author Bastien
 */
public class ChatSystem {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GUI fenetre = new GUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
