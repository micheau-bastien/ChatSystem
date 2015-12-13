/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.net.SocketException;

/**
 * First view of the ChatSystem, the user will set his nickname and connect.
 * @author Bastien
 */
public class GUIConnection extends JPanel implements ActionListener{
    private final JTextField nicknameTextField;
    private final JButton connectButton;
    private final GUIConnectionToGUI gUIConnectionToGUI;
    
    /**
     * Build the connection UI
     * @param gUIConnectionToGUI
     * @throws SocketException
     */
    public GUIConnection(GUIConnectionToGUI gUIConnectionToGUI) throws SocketException{
        this.gUIConnectionToGUI = gUIConnectionToGUI;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("User Name"));
        this.nicknameTextField = new JTextField("nickname");
        this.nicknameTextField.setMaximumSize(new Dimension(200, 30));
        this.nicknameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.nicknameTextField.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(nicknameTextField);

        this.connectButton = new JButton("Connect");
        this.connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.connectButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        connectButton.addActionListener(this);
        this.add(connectButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            gUIConnectionToGUI.connect(this.getNickname());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String getNickname(){
        return this.nicknameTextField.getText();
    }

}
