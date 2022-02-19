package com.bbstore.components;

import com.bbstore.models.Admin;
import com.bbstore.users.UserAuthenticator;

import javax.swing.*;
import java.awt.*;

public class AdminTile extends JPanel {
    private JPanel tile;
    private JLabel adminName;
    private JLabel adminEmail;
    private JButton removeButton;

    public AdminTile(Admin admin, UserAuthenticator authenticator){
        this.tile.setPreferredSize(new Dimension(548, 40));
        add(tile);

        this.adminName.setText(admin.getName()+(admin.getType().equals("super")?" *":""));
        this.adminEmail.setText(admin.getEmail());
        this.removeButton.setVisible(!authenticator.getAdminType().equals("admin"));
        System.out.println(authenticator.getAdminType());
        this.removeButton.addActionListener(e -> {
            try {
                admin.remove();
                this.tile.setVisible(false);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        });
    }
}
