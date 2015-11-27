/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author Bastien
 */
public class GUIConnection extends JPanel implements ActionListener{
    private final JTextField nicknameTextField;
    private final JButton connectButton;
    private final GUIConnectionToGUI gUIConnectionToGUI;
    
    public GUIConnection(GUIConnectionToGUI gUIConnectionToGUI) throws SocketException{
        this.gUIConnectionToGUI = gUIConnectionToGUI;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("User Name"));
        this.nicknameTextField = new JTextField("nickname");
        this.add(nicknameTextField);

        this.connectButton = new JButton("Connect");
        connectButton.addActionListener(this);
        this.add(connectButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            gUIConnectionToGUI.connect(this.nicknameTextField.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
