package com.bbstore.ui.uis;

import com.bbstore.ui.GUI;

import javax.swing.*;

public class Orders extends GUI {

    private JPanel ordersPanel;
    private JPanel orders;

    public Orders(){
        setSize(900, 600);
        setTitle("Add New Admin");
        setContentPane(this.ordersPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
