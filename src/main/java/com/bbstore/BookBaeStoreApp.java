package com.bbstore;

import com.bbstore.connection.DBConnection;
import com.bbstore.database.Database;

public class BookBaeStoreApp {
    DBConnection connection;
    Database database;

    public BookBaeStoreApp(DBConnection connection, Database database){
        this.connection = connection;
        this.database = database;
    }

    public void execute(){
        try {
            database.connect(connection.getConnection());

        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

}
