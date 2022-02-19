package com.bbstore.ui.uis;

import com.bbstore.ui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBook extends GUI {
    private JButton searchButton;
    private JTextField isbnField;
    private JPanel searchPanel;


    public SearchBook() {
        setSize(400, 300);
        setTitle("BookBae Store - Login");
        setContentPane(this.searchPanel);
        setResizable(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn=isbnField.getText();
            }
        });
    }




    public static void main(String[] args) {
        SearchBook searchBook = new SearchBook();
        searchBook.setVisible(true);
    }
}
