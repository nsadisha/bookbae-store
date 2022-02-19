package com.bbstore.ui.uis;

import com.bbstore.books.Book;
import com.bbstore.books.BookManager;
import com.bbstore.navigator.Navigator;
import com.bbstore.ui.GUI;

import javax.swing.*;
import java.util.Objects;

public class EditBook extends GUI {
    private JPanel homePanel;
    private JTextField authorNameField;
    private JTextField yearField;
    private JTextField editionField;
    private JTextField isbnField;
    private JComboBox<String> categoryBox;
    private JTextField bookNameField;
    private JTextField priceField;
    private JTextField availableQuantityField;
    private JTextField publisherNameField;
    private JComboBox<String> languageBox;
    private JTextArea bookDescriptionField;
    private JButton saveBookButton;
    private JButton cancelButton;

    private final String isbn;
    private final BookManager bookManager;

    public EditBook(String isbn, BookManager bookManager) {
        this.isbn = isbn;
        this.bookManager = bookManager;

        setSize(700,600);
        setTitle("Edit Book");
        setResizable(false);
        setAlwaysOnTop(true);
        setAutoRequestFocus(true);
        setLocationRelativeTo(null);
        setContentPane(this.homePanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        saveBookButton.addActionListener(e -> {
            String bookIsbn = isbnField.getText();
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
                boolean isBookUpdated=bookManager.editBook(
                        isbn,
                        new Book(
                                bookIsbn,
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
                if(isBookUpdated){
                    Navigator.pop();
                }

            }catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        });
        cancelButton.addActionListener(e -> Navigator.pop());
    }

    @Override
    protected void initState(){
        super.initState();
        loadData();

    }
    private void loadData(){
        try {
            Book book = bookManager.getBook(isbn);

            isbnField.setText(book.getIsbn());
            bookNameField.setText(book.getName());
            priceField.setText(book.getPrice());
            authorNameField.setText(book.getAuthor());
            publisherNameField.setText(book.getPublisher());
            yearField.setText(book.getYear().split("-")[0]);
            editionField.setText(book.getEdition());
            availableQuantityField.setText(book.getQuantity());
            languageBox.setSelectedItem(book.getLanguage());
            categoryBox.setSelectedItem(book.getCategory());
            bookDescriptionField.setText(book.getDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
