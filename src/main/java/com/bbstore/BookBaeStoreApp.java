package com.bbstore;

import com.bbstore.connection.DBConnection;
import com.bbstore.database.Database;
import com.bbstore.input.InputValidator;
import com.bbstore.navigator.Navigator;
import com.bbstore.users.UserAuthenticator;

public class BookBaeStoreApp {
    DBConnection connection;
    Database database;
    UserAuthenticator authenticator;
    InputValidator inputValidator;

    public BookBaeStoreApp(DBConnection connection, Database database, InputValidator inputValidator, UserAuthenticator authenticator){
        this.connection = connection;
        this.database = database;
        this.inputValidator = inputValidator;
        this.authenticator = authenticator;
    }

    public void execute(){
        try {
            database.connect(connection.getConnection());

            if(authenticator.isLoggedIn()){
                Navigator.push("home");
            }else{
                Navigator.push("login");
            }

        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

}
