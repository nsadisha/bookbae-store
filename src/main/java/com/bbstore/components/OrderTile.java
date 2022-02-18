package com.bbstore.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OrderTile extends JPanel {
    private JPanel accordion;
    private JLabel nameField;
    private JLabel statusField;

    public OrderTile(String name, String status){
        accordion.setPreferredSize(new Dimension(750,50));
        add(accordion);
        this.nameField.setText("#"+name);
        this.statusField.setText("Status: "+status);

        accordion.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(name);
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
