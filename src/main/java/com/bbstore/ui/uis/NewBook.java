package com.bbstore.ui.uis;

import com.bbstore.database.Database;
import com.bbstore.database.SQLDatabase;
import com.bbstore.input.InputValidator;
import com.bbstore.ui.GUI;
import com.bbstore.users.NewBookCreationFailedException;

import javax.swing.*;

public class NewBook extends GUI {
    private JPanel homePanel;
    private JTextField isbnField;
    private JComboBox categoryBox;
    private JComboBox languageBox;
    private JTextArea bookDescriptionField;
    private JButton cancelButton;
    private JButton addBookButton;
    private JTextField bookNameField;
    private JTextField authorNameField;
    private JTextField publisherNameField;
    private JTextField yearField;
    private JTextField priceField;
    private JTextField editionField;
    private JTextField availableQuantityField;

    private final Database database;
    private final InputValidator inputValidator;
    public NewBook(InputValidator inputValidator,Database database) {

        setSize(700,600);
        setTitle("Add new Book");
        setResizable(false);
        setContentPane(this.homePanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.database= database;
        this.inputValidator=inputValidator;

        addBookButton.addActionListener(e ->{
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

            boolean isNewBookCreated = false;

            try{
                isNewBookCreated=addNewBook(isbn,bookName,authorName,publisherName,year,price,edition,availableQuantity,language
                ,category,bookDescription);

            }catch (Exception ex) {
                ex.printStackTrace();
            }


        });


    }

    public static void main(String[] args) {

        InputValidator inputValidator=new InputValidator();
        Database db=new SQLDatabase() ;
        NewBook newBook = new NewBook(inputValidator,db);
        newBook.setVisible(true);
    }

    private boolean addNewBook(String isbn,String bookName,String authorName,String publisherName,String year,String price,String edition,
    String availableQuantity,String language,String category,String bookDescription) throws Exception{

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

            String query = "INSERT INTO books VALUES('"+isbn+ "','" +bookName+ "','" +price+ "','" +category+ "','" +language+ "','" +authorName+ "','" +year+ "','" +edition
                    + "','" +publisherName+ "','" +bookDescription+ "','" +availableQuantity+ "')";

            try {
                int addBook = database.updateQuery(query);

                return addBook == 1;
            }catch (Exception e){
                throw new NewBookCreationFailedException("New book creation failed!");
            }

        }



    }




}
