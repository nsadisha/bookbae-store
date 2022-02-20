package com.bbstore.ui.uis;

import com.bbstore.alert.AlertBox;
import com.bbstore.books.BookManager;
import com.bbstore.books.ISBNNotFoundException;
import com.bbstore.navigator.Navigator;
import com.bbstore.ui.GUI;

import javax.swing.*;

public class SearchBook extends GUI {
    private JButton searchButton;
    private JTextField isbnField;
    private JPanel searchPanel;

    public SearchBook(BookManager bookManager) {
        setSize(400, 300);
        setTitle("BookBae Store - Search");
        setContentPane(this.searchPanel);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        setDefaultCloseOperation(GUI.DISPOSE_ON_CLOSE);

        searchButton.addActionListener(e -> {
            String isbn=isbnField.getText();
            try {
                setAlwaysOnTop(false);
                if(bookManager.checkISBN(isbn)){
                    Navigator.openPopUp(new EditBook(isbn, bookManager));
                }else{
                    throw new ISBNNotFoundException("ISBN not found!");
                }
            } catch (Exception exception) {
                AlertBox.showAlert("Error", exception.getMessage(), JOptionPane.ERROR_MESSAGE);
            } finally {
                setAlwaysOnTop(true);
            }
        });
    }
}
