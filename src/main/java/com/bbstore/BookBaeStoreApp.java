package com.bbstore;

import com.bbstore.connection.DBConnection;
import com.bbstore.database.Database;
import com.bbstore.input.InputValidator;
import com.bbstore.ui.uis.LogIn;
import com.bbstore.users.UserAuthenticator;

public class BookBaeStoreApp {
    DBConnection connection;
    Database database;
    UserAuthenticator authenticator;
    InputValidator inputValidator;

    public BookBaeStoreApp(DBConnection connection, Database database, InputValidator inputValidator){
        this.connection = connection;
        this.database = database;
        this.inputValidator = inputValidator;
    }

    public void execute(){
        try {
            database.connect(connection.getConnection());
            authenticator = new UserAuthenticator(this.database);

            LogIn login = new LogIn(authenticator, this.inputValidator);
            login.setVisible(true);

        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

}
