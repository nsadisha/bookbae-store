package com.bbstore.books;

import com.bbstore.database.Database;
import com.bbstore.input.InputValidator;

import java.sql.ResultSet;

public class BookManager {
    private final Database database;
    private final InputValidator validator;

    public BookManager(Database database, InputValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public boolean checkISBN(String isbn) throws Exception{
        try{
            ResultSet res = database.executeQuery("SELECT isbn FROM books WHERE isbn='"+isbn+"'");

            if(res.next()){
                return true;
            }else{
                throw new ISBNNotFoundException("ISBN not found!");
            }
        }catch(ISBNNotFoundException e){
            throw new ISBNNotFoundException(e.getMessage());
        }catch(Exception e){
            throw new BookSearchFailedException("Search failed: "+e.getMessage());
        }
    }
    public boolean addNewBook(Book book) throws Exception{
        if(!validator.validateField(book.getIsbn())){
            throw new NewBookCreationFailedException("ISBN has at least 1 character!");
        }else if(!validator.validateField(book.getName())) {
            throw new NewBookCreationFailedException("Name has at least 1 character!");
        }else if(!validator.validateField(book.getAuthor())) {
            throw new NewBookCreationFailedException("Author name has at least 1 character!");
        }else if(!validator.validateField(book.getPublisher())) {
            throw new NewBookCreationFailedException("Publisher name has at least 1 character!");
        }else if(!validator.validateField(book.getYear())) {
            throw new NewBookCreationFailedException("Year is required!");
        }else if(!validator.validateField(book.getPrice())) {
            throw new NewBookCreationFailedException("Price is required!");
        }else if(!validator.validateField(book.getEdition())) {
            throw new NewBookCreationFailedException("Edition is required!");
        }else if(!validator.validateField(book.getQuantity())) {
            throw new NewBookCreationFailedException("Quantity is required");
        }else if(!validator.validateField(book.getDescription())) {
            throw new NewBookCreationFailedException("Description has at least 1 character");
        }else {
            String query = "INSERT INTO books VALUES('"+book.getIsbn()+ "','" +book.getName()+ "','" +
                    book.getPrice()+ "','" + book.getCategory()+ "','" +book.getLanguage()+ "','" +
                    book.getAuthor()+ "','" +book.getYear()+ "','" +book.getEdition() + "','" +
                    book.getPublisher()+ "','" +book.getDescription()+ "','" +book.getQuantity()+ "')";

            try {
                int addBook = database.updateQuery(query);
                return addBook == 1;
            }catch (Exception e){
                e.printStackTrace();
                throw new NewBookCreationFailedException("New book creation failed!: "+e.getMessage());
            }
        }
    }
}
