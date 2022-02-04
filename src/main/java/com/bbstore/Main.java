package com.bbstore;

import com.bbstore.connection.DBConnection;
import com.bbstore.connection.SQLConnection;
import com.bbstore.database.Database;
import com.bbstore.database.SQLDatabase;
import com.bbstore.reader.JSONReader;

public class Main {
    public static void main(String[] args){
        JSONReader dbCredentials = new JSONReader("src/main/resources/db.json");
        DBConnection connection = new SQLConnection(
                dbCredentials.get("host"),
                dbCredentials.get("username"),
                dbCredentials.get("password")
        );
        Database db = new SQLDatabase();

        BookBaeStoreApp bookBaeStoreApplication = new BookBaeStoreApp(connection, db);

        //start the app
        bookBaeStoreApplication.execute();
    }
}
