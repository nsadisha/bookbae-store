package com.bbstore.books;

import com.bbstore.database.Database;
import com.bbstore.input.InputValidator;
import com.bbstore.input.InvalidInputException;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class BookManager {
    private final Database database;
    private final InputValidator validator;

    public BookManager(Database database, InputValidator validator){
        this.database = database;
        this.validator = validator;
    }
    private boolean validateBook(Book book) throws Exception{
        if(!validator.validateField(book.getIsbn())){
            throw new InvalidInputException("ISBN has at least 1 character!");
        }else if(!validator.validateField(book.getName())) {
            throw new InvalidInputException("Name has at least 1 character!");
        }else if(!validator.validateField(book.getAuthor())) {
            throw new InvalidInputException("Author name has at least 1 character!");
        }else if(!validator.validateField(book.getPublisher())) {
            throw new InvalidInputException("Publisher name has at least 1 character!");
        }else if(!validator.validateField(book.getYear())) {
            throw new InvalidInputException("Year is required!");
        }else if(!validator.validateField(book.getPrice())) {
            throw new InvalidInputException("Price is required!");
        }else if(!validator.validateField(book.getEdition())) {
            throw new InvalidInputException("Edition is required!");
        }else if(!validator.validateField(book.getQuantity())) {
            throw new InvalidInputException("Quantity is required");
        }else if(!validator.validateField(book.getDescription())) {
            throw new InvalidInputException("Description has at least 1 character");
        }else{
            return true;
        }
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
        if(validateBook(book)){
            String query = "INSERT INTO books VALUES('"+book.getIsbn()+ "','" +book.getName()+ "','" +
                    book.getPrice()+ "','" + book.getCategory()+ "','" +book.getLanguage()+ "','" +
                    book.getAuthor()+ "','" +book.getYear()+ "','" +book.getEdition() + "','" +
                    book.getPublisher()+ "','" +book.getDescription()+ "','" +book.getQuantity()+ "')";

            try {
                int addBook = database.updateQuery(query);
                return addBook == 1;
            }catch(InvalidInputException e){
                throw new InvalidInputException(e.getMessage());
            }catch(SQLIntegrityConstraintViolationException e){
                throw new NewBookCreationFailedException("ISBN is already available!");
            }catch (Exception e){
                throw new NewBookCreationFailedException("New book creation failed!: "+e.getMessage());
            }
        }

        return false;
    }
    public boolean editBook(String isbn, Book book) throws Exception{
        if(validateBook(book)){
            String query = "UPDATE books SET name='" +book.getName()+ "', price='" +
                    book.getPrice()+ "', category='" +book.getCategory()+ "', language='" +book.getLanguage()+ "', author='" +
                    book.getAuthor()+ "', year=\"" +book.getYear()+ "\", edition='" +book.getEdition() + "', publisher='" +
                    book.getPublisher()+ "', description='" +book.getDescription()+ "', available_quantity='" +
                    book.getQuantity()+ "' WHERE isbn='"+isbn+"'";
            try {
                int updateBook = database.updateQuery(query);

                return updateBook == 1;
            }catch(InvalidInputException e){
                throw new InvalidInputException(e.getMessage());
            }catch(SQLIntegrityConstraintViolationException e){
                throw new BookUpdateFailedException("New ISBN is already available!");
            }catch (Exception e){
                throw new BookUpdateFailedException("Updating book failed!: "+e.getMessage());
            }
        }

        return false;
    }
    public Book getBook(String isbn) throws Exception{
        try{
            ResultSet res = database.executeQuery("SELECT * FROM books WHERE isbn='"+isbn+"'");

            if(res.next()){
                return new Book(
                        res.getString("isbn"),
                        res.getString("name"),
                        res.getString("author"),
                        res.getString("publisher"),
                        res.getString("year"),
                        res.getString("price"),
                        res.getString("edition"),
                        res.getString("available_quantity"),
                        res.getString("language"),
                        res.getString("category"),
                        res.getString("description")
                );
            }else{
                throw new BookSearchFailedException("Book not available!");
            }
        }catch(Exception e){
            throw new BookSearchFailedException("Cannot get book details: "+e.getMessage());
        }
    }
}
