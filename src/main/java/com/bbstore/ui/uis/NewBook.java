package com.bbstore.ui.uis;

import com.bbstore.database.Database;
import com.bbstore.users.UserAuthenticator;

import javax.swing.*;
import java.text.DecimalFormat;

public class NewBook extends JFrame {
    private JPanel homePanel;
    private JTextField emailField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea textArea1;
    private JButton addBookButton;



    public NewBook() {

        setSize(700,500);
        setContentPane(this.homePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        NewBook newBook = new NewBook();
        newBook.setVisible(true);
    }



}
