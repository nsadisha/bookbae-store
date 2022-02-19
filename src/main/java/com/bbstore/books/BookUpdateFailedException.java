package com.bbstore.books;

public class BookUpdateFailedException extends Exception{
    public BookUpdateFailedException(String msg){
        super(msg);
    }
}
