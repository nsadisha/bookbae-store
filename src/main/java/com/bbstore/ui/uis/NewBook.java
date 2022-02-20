package com.bbstore.ui.uis;

import com.bbstore.alert.AlertBox;
import com.bbstore.books.Book;
import com.bbstore.books.BookManager;
import com.bbstore.input.InvalidInputException;
import com.bbstore.navigator.Navigator;
import com.bbstore.ui.GUI;

import javax.swing.*;
import java.util.Objects;

public class NewBook extends GUI {
    private JPanel homePanel;
    private JTextField isbnField;
    private JComboBox<String> categoryBox;
    private JComboBox<String> languageBox;
    private JTextArea bookDescriptionField;
    private JButton addBookButton;
    private JTextField bookNameField;
    private JTextField authorNameField;
    private JTextField publisherNameField;
    private JTextField yearField;
    private JTextField priceField;
    private JTextField editionField;
    private JTextField availableQuantityField;
    private JButton cancelButton;

    public NewBook(BookManager bookManager) {
        setSize(700,600);
        setTitle("Add New Book");
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        setContentPane(this.homePanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addBookButton.addActionListener(e ->{
            String isbn = isbnField.getText();
            String bookName = bookNameField.getText();
            String authorName=authorNameField.getText();
            String publisherName= publisherNameField.getText();
            String year=yearField.getText();
            String price=priceField.getText();
            String edition=editionField.getText();
            String availableQuantity = availableQuantityField.getText();
            String language= Objects.requireNonNull(languageBox.getSelectedItem()).toString();
            String category= Objects.requireNonNull(categoryBox.getSelectedItem()).toString();
            String bookDescription=bookDescriptionField.getText();

            try{
                setAlwaysOnTop(false);
                boolean isNewBookCreated = bookManager.addNewBook(
                        new Book(
                                isbn,
                                bookName,
                                authorName,
                                publisherName,
                                year,
                                price,
                                edition,
                                availableQuantity,
                                language,
                                category,
                                bookDescription
                        )
                );

                if(isNewBookCreated){
                    Navigator.pop();
                }
            }catch (InvalidInputException exception) {
                AlertBox.showAlert("Warning", exception.getMessage(), JOptionPane.WARNING_MESSAGE);
            }catch(Exception exception){
                AlertBox.showAlert("Error", exception.getMessage(), JOptionPane.ERROR_MESSAGE);
            }finally {
                setAlwaysOnTop(true);
            }
        });
        cancelButton.addActionListener(e -> Navigator.pop());
    }
}
