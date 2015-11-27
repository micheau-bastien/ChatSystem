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
    public void connect(String nickname)  throws IOException ;
    
}
