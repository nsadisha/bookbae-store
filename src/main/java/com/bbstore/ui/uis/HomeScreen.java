package com.bbstore.ui.uis;

import java.awt.*;
import com.bbstore.ui.GUI;
import com.bbstore.users.UserAuthenticator;

import javax.swing.*;
import com.intellij.uiDesigner.core.*;

public class HomeScreen extends GUI {

    private final UserAuthenticator authenticator;

    public HomeScreen(UserAuthenticator authenticator){
        initComponents();
        initComponents();
        this.authenticator = authenticator;
//        setSize(1200,650);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        setTitle("BookBae Store - Dashboard");
        setContentPane(this.home);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        home = new JPanel();

        //======== home ========
        {
            home.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
            .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
            .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
            Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
            ),home. getBorder()));home. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
            public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
            )))throw new RuntimeException();}});
            home.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel home;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
