/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.test;

import org.insa.chatsystem.gui.*;
/**
 *
 * @author Bastien
 */
public class TestGUI {

    /**
     *
     * @param args
     */
    public static void main(String[] args){       
        try {
            GUI fenetre = new GUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
