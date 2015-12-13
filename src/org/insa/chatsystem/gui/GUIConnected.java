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
 * Connected GUI. This class could and should be singleton.
 * @author Laure&Bastien 
*/
public class GUIConnected extends JPanel implements KeyListener, GuiToGuiConnected, ActionListener, ListSelectionListener {
    
    private final GUIConnectedToGUI gUIConnectedToGUI;
    
    private JLabel titre;
    private JButton logoutButton, sendButton;
    private JPanel rightPan, leftPan;
    private JList<User> list;
    private User selectedUser;
    private JTextField textToSend;
    private JTextArea messagesList;
    private final Color background = Color.decode("#F5F5F5");
    private final Color leftMenuColor = Color.decode("#F5F5F5");
    
    //hPane c'est notre premier panel splité en deux (la userliste et la partie onglets et messages à envoyer)
    private final JSplitPane hPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    
    /**
     * Buil the GUI Connected
     * @param gUIConnectedToGUI
     */
    public GUIConnected(GUIConnectedToGUI gUIConnectedToGUI) {
        this.hPane.setBackground(background);
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
        this.leftPan = new JPanel();
        this.leftPan.setBackground(this.leftMenuColor);
        this.leftPan.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY));
        //this.leftPan.setLayout(new BoxLayout(leftPan, BoxLayout.Y_AXIS));
        this.leftPan.setLayout(new GridLayout(3, 0, 20, 20));
        //Fill Left Pane with initialized elements
        this.initLogoutButton();
        this.initList();
        
        leftPan.add(new JLabel("Connected User List"));
        leftPan.add(list);
        leftPan.add(this.logoutButton);
    }
    
    private void initRightPan(){
        this.messagesList = new JTextArea();
        this.messagesList.setEditable(false);
        this.messagesList.setBorder(BorderFactory.createEmptyBorder());
        this.messagesList.setBackground(background);
        JScrollPane scrollMessages = new JScrollPane(this.messagesList);
        scrollMessages.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
        
        this.textToSend = new JTextField();
        this.textToSend.setMaximumSize(new Dimension(this.textToSend.getMaximumSize().width, 50));
        this.textToSend.addKeyListener(this);
        
        this.sendButton = new JButton("Send");
        this.sendButton.addActionListener(this);

        this.rightPan = new JPanel();
        this.rightPan.setBackground(background);
        this.rightPan.setLayout(new BoxLayout(this.rightPan, BoxLayout.Y_AXIS));
        titre = new JLabel("Messages");
        titre.setFont(new Font("helvetica neue", Font.PLAIN, 18));
        this.rightPan.add(new JLabel("  "));
        this.rightPan.add(titre);
        this.rightPan.add(new JLabel("  "));
        this.rightPan.add(scrollMessages);
        this.rightPan.add(this.textToSend);
        this.rightPan.add(this.sendButton);
    }
    
    private void initList(){
        this.list = new JList(this.gUIConnectedToGUI.fetchUserList());
        this.list.setSelectedIndex(0);
        this.list.addListSelectionListener(this);
        this.list.setFont(new Font("helvetica neue", Font.PLAIN, 15));
        this.list.setBackground(this.leftMenuColor);
        this.setMaximumSize(new Dimension(100, 10));
    }
    
    private void initLogoutButton(){
        this.logoutButton = new JButton("Logout");
        this.logoutButton.addActionListener(this);
        this.logoutButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
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
            if (this.list.getSelectedValue() == null){
                this.selectedUser = this.gUIConnectedToGUI.fetchUserList().get(0);
            }else{
                this.selectedUser = this.list.getSelectedValue();
            }
            this.selectedUser.resetUnreadMessages();
            System.out.println("SelectedUser in list : " + this.list.getSelectedValue());
            this.printMessagesWith(this.selectedUser);
            this.titre.setText("Messages with "+this.selectedUser.getNickname());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sendButton){
            try {
                gUIConnectedToGUI.sendMessage(this.selectedUser, this.textToSend.getText());
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

    /**
     * Will display the received message from the Controller to the Connected GUI to be displayed.
     * @param user
     * @param message
     */
    @Override
    public void newMessage(User user, String message) {
        System.out.println("SelectedUser : "+this.selectedUser);
        if (this.selectedUser == null){
            this.selectedUser = this.gUIConnectedToGUI.fetchUserList().get(0);
            this.list.setSelectedValue(this.gUIConnectedToGUI.fetchUserList().get(0), true);
            /*this.selectedUser = user;
            this.list.setSelectedValue(this.gUIConnectedToGUI.fetchUserList().indexOf(user), true);*/
        } else if (this.selectedUser.getAddress().equals(user.getAddress())){
            user.resetUnreadMessages();
            this.messagesList.setText(this.messagesList.getText() + "\n" + user.getNickname()+ ": " + message);
//this.refreshMessagesWith(user);
        }else{
            this.list.setModel(this.gUIConnectedToGUI.fetchUserList());
            this.list.repaint();
            System.out.println("MESSAGE RECU SUR UNE AUTRE CONV");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getKeyCode() != KeyEvent.VK_ALT){
            try {
                gUIConnectedToGUI.sendMessage(this.selectedUser, this.textToSend.getText());
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

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * When passing from disconnected state to connected state, you need to update the userlist.
     */
    @Override
    public void connect() {
        // To test : Peut surement être supprimé depuis que l'on reset proprement le model des users.
        this.list.setModel(this.gUIConnectedToGUI.fetchUserList());
    }
}