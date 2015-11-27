/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.net.SocketException;

/**
 *
 * @author Bastien
 */
public class GUIConnected extends GUI {
    public GUIConnected() throws SocketException {
        //Ajouter l'user dans le titre de la fenêtre
        this.setTitle("Chat Système");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
    }
    
    public void printMessage(String Message, String nicknameExp) {
        // TODO
    };
}
