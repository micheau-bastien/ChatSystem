/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.io.IOException;

/**
 *
 * @author Bastien
 */
public interface GuiToController {
    public void connect(String nickname)  throws IOException ;
    public void sendMessage(String nickname);
    
    //Throws exception ?
    public void logout();
}
