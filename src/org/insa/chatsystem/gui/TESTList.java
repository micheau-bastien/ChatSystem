/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.gui;

import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.insa.chatsystem.users.User;
import org.insa.chatsystem.users.UserList;

/**
 *
 * @author Bastien
 */
public class TESTList extends JPanel implements ListSelectionListener {
    JList jlist;
    UserList userList;
    
    public TESTList(UserList ul){
        this.jlist = new JList(ul.getUserList().toArray());
        this.jlist.addListSelectionListener(this);
        this.add(this.jlist);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println(e);
        System.out.println(this.jlist.getSelectedIndex());
        System.out.println(this.jlist.getSelectedValue());
    }
}
