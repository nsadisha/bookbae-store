package com.bbstore.components;

import com.bbstore.navigator.Navigator;
import com.bbstore.ui.uis.ViewOrder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrderTile extends JPanel {
    private JPanel accordion;
    private JLabel nameField;
    private JLabel statusField;

    public OrderTile(String orderId, String status){
        accordion.setPreferredSize(new Dimension(750,50));
        add(accordion);
        this.nameField.setText("#"+orderId);
        this.statusField.setText("Status: "+status);

        accordion.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Navigator.openPopUp(new ViewOrder(orderId));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
