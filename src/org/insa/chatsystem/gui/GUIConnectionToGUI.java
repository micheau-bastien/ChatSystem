/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.io.IOException;

/**
 *
 * @author Bastien
 */
public interface GUIConnectionToGUI {

    /**
     * Asks to the GUI to connect the local user with the choosen nickname.
     * @param nickname
     * @throws IOException
     */
    public void connect(String nickname)  throws IOException ;
    
}
