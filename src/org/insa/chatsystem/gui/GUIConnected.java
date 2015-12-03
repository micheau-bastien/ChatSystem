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
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;

/**
 * 
 * @author Laure 
*/
public class GUIConnected extends JPanel implements GUIToGUIConnected, ListSelectionListener {
    
    private final GUIConnectedToGUI gUIConnectedToGUI;
    private int tabCounter = 0;
    
    private JButton logoutButton, chatWithButton, sendButton, closeButton;
    private JPanel groupChatTab;
    private JList<User> liste;
    private User selectedUser;
    
<<<<<<< Updated upstream
    //hPane c'est notre premier panel splité en deux (la userliste et la partie onglets et messages à envoyer)
    private JSplitPane hPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    JTabbedPane rightPan;
    JPanel leftPan;
=======
    ///////////////////////////TEST POUR LA USER LIST///////////////
    private JList list = new JList();
    private JLabel listLabel = new JLabel("Connected User List");
    private String choix[] = {" Pierre:192.256.5.6", " Paul:129.168.45.3", " Jacques", " Lou", " Marie"};
    
    
>>>>>>> Stashed changes
    
    public GUIConnected(final GUIConnectedToGUI gUIConnectedToGUI) {
        this.gUIConnectedToGUI = gUIConnectedToGUI;
        this.setLayout(new BorderLayout(2,2));
        
<<<<<<< Updated upstream
        //Create Right Pane
        this.rightPan = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        this.rightPan.addTab("Group Chat Room", this.groupChatTab);
        
        //Fill Right Pane with initialized elements
        this.initGroupeChatTab();
        this.hPane.setRightComponent(rightPan);
       
        //Create Left Pane
        this.leftPan = new JPanel();
        this.leftPan.setLayout(new BoxLayout(leftPan, BoxLayout.Y_AXIS));
=======
        ///////////////////TEST LIST//////////////////////////
        //list = new JList((ListModel) this.gUIConnectedToGUI.fetchUserList());
        list = new JList(choix);
        list.addListSelectionListener(this);
        ///////////////////TEST LIST//////////////////////////

        
        
>>>>>>> Stashed changes
        
        //Fill Left Pane with initialized elements
        this.initChatWithButton();
        this.initLogoutButton();
        this.initListe();
        leftPan.add(new JLabel("Connected User List"), BorderLayout.WEST);
        leftPan.add(liste, BorderLayout.EAST);
        leftPan.add(this.chatWithButton, BorderLayout.SOUTH);
        leftPan.add(this.logoutButton, BorderLayout.SOUTH);
        
        this.hPane.setLeftComponent(leftPan);
        this.add(hPane);
    }
    
    private void initListe(){
        this.liste = new JList(this.gUIConnectedToGUI.fetchUserList().getUserList().toArray());
        this.liste.addListSelectionListener(this); 
    }
    
    private void initGroupeChatTab() {
        this.groupChatTab = new JPanel();
        this.groupChatTab.setLayout(new BoxLayout(groupChatTab, BoxLayout.Y_AXIS));
        this.groupChatTab.add(new JLabel("conversation de groupe"));
        this.groupChatTab.add(new JTextField("msg"));
        this.groupChatTab.add(new JButton("send msg"));
    }
    
    private void initChatWithButton(){
        this.chatWithButton = new JButton("Chat With");
        this.chatWithButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Il faut rajouter la valeur du champ user sélectionné
                newChatTab();
            }
        });
    }
    
    private void initLogoutButton(){
        this.logoutButton = new JButton("Logout");
        this.logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    gUIConnectedToGUI.logout();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
<<<<<<< Updated upstream
=======
        
        leftPan.add(listLabel, BorderLayout.WEST);
        leftPan.add(list, BorderLayout.EAST);
        leftPan.add(chatWith, BorderLayout.SOUTH);
        leftPan.add(logoutButton, BorderLayout.SOUTH);
        
        hPane.setLeftComponent(leftPan);
        hPane.setSize(700, 700);
        add(hPane);
                
    }
    

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Logout: Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> Stashed changes
    }
    
    

    //Fonction qui créé un onglet pour les conversations. TODO : en fonction du user sélectionné dans la user list
    public void newChatTab() {
        final JPanel content = new JPanel();
        JPanel tab = new JPanel();
        tab.setOpaque(false);
        
        JLabel labelOnglet = new JLabel("Onglet " + (++tabCounter));

        JButton boutonFermer = new JButton("Close Chat");
        boutonFermer.setSize(30, 30);
        boutonFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int closeTabNumber = rightPan.indexOfComponent(content);
                rightPan.removeTabAt(closeTabNumber);
            }
        });

        
        tab.add(labelOnglet);
        
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(new JLabel("conversation avec truc "+rightPan.getTabCount()));
        content.add(new JTextField("msg"));
        content.add(new JButton("send msg"));
        content.add(boutonFermer);

        rightPan.addTab(null, content);
        rightPan.setTabComponentAt(rightPan.getTabCount() - 1, tab);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.selectedUser = this.liste.getSelectedValue();
    }
}