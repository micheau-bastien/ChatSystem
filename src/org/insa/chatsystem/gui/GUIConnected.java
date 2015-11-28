/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.net.SocketException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

/**
 * 
 * @author Laure 
*/
public class GUIConnected extends JPanel implements ActionListener, GUIToGUIConnected {
    
    private final JButton logoutButton;
    private JButton chatWith = new JButton("Chat With");
    //hPane c'est notre premier panel splité en deux (la userliste et la partie onglets et messages à envoyer)
    private JSplitPane hPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    //pan de droite avec les onglets
    JTabbedPane rightPan = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
     
    private int tabCounter = 0;
    
    public GUIConnected() {
        // PB : taille de la fenêtre pas bonne, et le panel ne se resize pas comme il faut
        
        chatWith.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                newChatTab();
            }
        });
        
        

        JPanel groupChatTab = new JPanel();
        groupChatTab.setLayout(new BoxLayout(groupChatTab, BoxLayout.Y_AXIS));
        groupChatTab.add(new JLabel("conversation de groupe"));
        groupChatTab.add(new JTextField("msg"));
        groupChatTab.add(new JButton("send msg"));
        
        rightPan.addTab("Group Chat Room", groupChatTab);
        //rightPan.setComponentAt(0 , groupChatTab);

        hPane.setRightComponent(rightPan);
       
        JPanel leftPan = new JPanel();
        leftPan.setLayout(new BoxLayout(leftPan, BoxLayout.Y_AXIS));
        leftPan.add(new JLabel("User List: "));
        leftPan.add(new JLabel("-User1 \n-User2"));
        
        this.logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    throw new UnsupportedOperationException("Logout: Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
        leftPan.add(chatWith, BorderLayout.SOUTH);
        leftPan.add(logoutButton, BorderLayout.SOUTH);
        
        hPane.setLeftComponent(leftPan);
        
        add(hPane);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Logout: Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}