/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.insa.chatsystem.messages.MessageList;
import org.insa.chatsystem.messages.MessageTextExchanged;
import org.insa.chatsystem.users.User;

/**
 * 
 * @author Laure 
*/
public class GUIConnected extends JPanel implements KeyListener, GuiToGuiConnected, ActionListener, ListSelectionListener {
    
    private final GUIConnectedToGUI gUIConnectedToGUI;
    
    private JButton logoutButton, sendButton;
    private JPanel rightPan, leftPan;
    private JList<User> list;
    private User selectedUser;
    private JTextArea textToSend;
    private JTextArea messagesList;
    
    //hPane c'est notre premier panel splité en deux (la userliste et la partie onglets et messages à envoyer)
    private JSplitPane hPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    
    public GUIConnected(GUIConnectedToGUI gUIConnectedToGUI) {
        this.gUIConnectedToGUI = gUIConnectedToGUI;
        this.setLayout(new BorderLayout(2,2));
        
        this.initRightPan();
        this.initLeftPan();
        
        //Fill Right Pane with initialized elements
        this.hPane.setRightComponent(rightPan);
        this.hPane.setLeftComponent(leftPan);
        this.add(hPane);
    }
    
    private void initLeftPan(){
        //Create Left Pane
        this.leftPan = new JPanel();
        this.leftPan.setLayout(new BoxLayout(leftPan, BoxLayout.Y_AXIS));
        
        //Fill Left Pane with initialized elements
        this.initLogoutButton();
        this.initList();
        leftPan.add(new JLabel("Connected User List"), BorderLayout.WEST);
        leftPan.add(list, BorderLayout.EAST);
        leftPan.add(this.logoutButton, BorderLayout.SOUTH);
    }
    
    private void initRightPan(){
        this.messagesList = new JTextArea();
        this.messagesList.setEditable(false);
        JScrollPane scrollMessages = new JScrollPane(this.messagesList);
        
        this.textToSend = new JTextArea();
        this.textToSend.setMaximumSize(new Dimension(this.textToSend.getMaximumSize().width, 50));
        this.textToSend.addKeyListener(this);
        
        this.sendButton = new JButton("Send");
        this.sendButton.addActionListener(this);

        this.rightPan = new JPanel();
        this.rightPan.setLayout(new BoxLayout(this.rightPan, BoxLayout.Y_AXIS));
        this.rightPan.add(new JLabel("Messages"));
        this.rightPan.add(scrollMessages);
        this.rightPan.add(this.textToSend);
        this.rightPan.add(this.sendButton);
    }
    
    private void initList(){
        this.list = new JList(this.gUIConnectedToGUI.fetchUserList());
        this.selectedUser = this.gUIConnectedToGUI.fetchUserList().get(0);
        this.list.setSelectedValue(this.selectedUser, true);
        this.list.addListSelectionListener(this); 
    }
    
    private void initLogoutButton(){
        this.logoutButton = new JButton("Logout");
        this.logoutButton.addActionListener(this);
    }
    
    private void printMessagesWith(User user) throws UnknownHostException {
        System.out.println("user : " + user);
        MessageList messageExchanged = MessageList.with(user.getAddress());
        this.messagesList.setText("");
        for(MessageTextExchanged mte : messageExchanged){
            if (mte.getSource().equals(InetAddress.getLocalHost())){
                this.messagesList.setText(this.messagesList.getText()+"\n"+"you: "+mte.getMessage().getMessage());
            }else{
                this.messagesList.setText(this.messagesList.getText()+"\n"+user.getNickname()+": "+mte.getMessage().getMessage());
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            this.selectedUser = this.list.getSelectedValue();
            this.messagesList.setText("");
            System.out.println("SelectedUser in list : " + this.list.getSelectedValue());
            this.printMessagesWith(this.list.getSelectedValue());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sendButton){
            try {
                gUIConnectedToGUI.send(this.textToSend.getText(), this.selectedUser.getAddress());
                this.messagesList.setText(this.messagesList.getText()+"\n"+"you : " +this.textToSend.getText());
                this.textToSend.setText("");
                this.messagesList.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getSource() == this.logoutButton){
            try {
                gUIConnectedToGUI.logout();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void newMessage(User user, String message) {
        System.out.println("SelectedUser : "+this.selectedUser);
        if (this.selectedUser == null){
            this.selectedUser = this.gUIConnectedToGUI.fetchUserList().get(0);
            this.list.setSelectedValue(this.gUIConnectedToGUI.fetchUserList().get(0), true);
        } else if (this.selectedUser.getAddress().equals(user.getAddress())){
            this.messagesList.setText(this.messagesList.getText() + "\n" + user.getNickname() + message);
//this.refreshMessagesWith(user);
        }else{
            System.out.println("MESSAGE RECU SUR UNE AUTRE CONV");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
  
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getKeyCode() != KeyEvent.VK_ALT){
            try {
                System.out.println("guicotogui : "+gUIConnectedToGUI);
                System.out.println("textosent : "+this.textToSend);
                System.out.println("selectedUser : "+ this.selectedUser);
                gUIConnectedToGUI.send(this.textToSend.getText(), this.selectedUser.getAddress());
                this.messagesList.setText(this.messagesList.getText()+"\n"+"you : " +this.textToSend.getText());
                this.textToSend.setText("");
                this.messagesList.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getKeyCode() == KeyEvent.VK_ALT){
            this.textToSend.setText(this.textToSend.getText()+"\n");
        }
    }
}