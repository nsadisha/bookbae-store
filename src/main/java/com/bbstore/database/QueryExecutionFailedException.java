package com.bbstore.database;

public class QueryExecutionFailedException extends Exception{
    public QueryExecutionFailedException(String message){
        super(message);
    }
}
