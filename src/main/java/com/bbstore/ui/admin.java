package com.bbstore.ui;


import javax.swing.*;

public class admin extends JFrame{
    private JPanel adminpanel;

    public admin() {
        this.adminpanel = adminpanel;
        setContentPane(adminpanel);
        setSize(1200,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {
        admin ad=new admin();
        ad.setVisible(true);

    }
}
