package com.bbstore.ui.uis;

import com.bbstore.database.Database;
import com.bbstore.database.QueryExecutionFailedException;
import com.bbstore.database.SQLDatabase;
import com.bbstore.input.InputValidator;
import com.bbstore.ui.GUI;
import com.bbstore.users.NewBookCreationFailedException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditBook extends GUI {
    private JPanel homePanel;
    private JTextField authorNameField;
    private JTextField yearField;
    private JTextField editionField;
    private JTextField isbnField;
    private JComboBox categoryBox;
    private JTextField bookNameField;
    private JTextField priceField;
    private JTextField availableQuantityField;
    private JTextField publisherNameField;
    private JComboBox languageBox;
    private JTextArea bookDescriptionField;
    private JButton saveBookButton;

    private  final InputValidator inputValidator;
    private final Database database;

    public EditBook(String isbn, InputValidator inputValidator, Database database) {
        this.inputValidator = inputValidator;
        this.database = database;


        setSize(700,600);
        setTitle("Add new Book");
        setResizable(false);
        setContentPane(this.homePanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        fillData(isbn);



        saveBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                String bookName = bookNameField.getText();
                String authorName=authorNameField.getText();
                String publisherName= publisherNameField.getText();
                String year=yearField.getText();
                String price=priceField.getText();
                String edition=editionField.getText();
                String availableQuantity = availableQuantityField.getText();
                String language= languageBox.getActionCommand();
                String category=categoryBox.getActionCommand();
                String bookDescription=bookDescriptionField.getText();

                boolean isBookUpdated = false;

                try{
                    isBookUpdated=updateBook(isbn,bookName,authorName,publisherName,year,price,edition,availableQuantity,language
                            ,category,bookDescription);

                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        String isbn="034914043";
        InputValidator inputValidator=new InputValidator();
        Database database=new SQLDatabase();
        EditBook editBook=new EditBook(isbn, inputValidator, database);
        editBook.setVisible(true);
    }

    private void fillData(String isbn){


        try {
            ResultSet resultSet=database.executeQuery("SELECT * FROM books where isbn ='"+isbn+"'");

            isbnField.setText(resultSet.getString("isbn"));
            bookNameField.setText(resultSet.getString("name"));
            priceField.setText(resultSet.getString("price"));
            authorNameField.setText(resultSet.getString("author"));
            yearField.setText(resultSet.getString("year"));
            editionField.setText(resultSet.getString("edition"));
            availableQuantityField.setText(resultSet.getString("available_quantity"));
            languageBox.setSelectedItem(resultSet.getString("language"));
            categoryBox.setSelectedItem(resultSet.getString("category"));
            bookDescriptionField.setText(resultSet.getString("description"));


        } catch (QueryExecutionFailedException | SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean updateBook(String isbn,String bookName,String authorName,String publisherName,String year,String price,String edition,
                               String availableQuantity,String language,String category,String bookDescription)throws Exception{

        if(!inputValidator.validateField(isbn)){
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(bookName)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(authorName)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(publisherName)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(year)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(price)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(edition)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(availableQuantity)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else if(!inputValidator.validateField(bookDescription)) {
            throw new NewBookCreationFailedException("There should be at least 1 character ");
        }else {

            String query = "UPDATE books SET isbn='"+isbn+ "',name='" +bookName+ "',price'" +price+ "',category'" +category+ "',language'" +language+ "',author'" +authorName+ "',year'" +year+ "',edition'" +edition
                    + "',publisher'" +publisherName+ "',description'" +bookDescription+ "',available_quantity'" +availableQuantity+ "')";

            try {
                int updateBook = database.updateQuery(query);

                return updateBook == 1;
            }catch (Exception e){
                throw new NewBookCreationFailedException("Updating book failed!");
            }

        }


    }
}
