package com.bbstore;

import com.bbstore.connection.DBConnection;
import com.bbstore.connection.SQLConnection;
import com.bbstore.cookies.CookieManager;
import com.bbstore.database.Database;
import com.bbstore.database.SQLDatabase;
import com.bbstore.input.InputValidator;
import com.bbstore.reader.JSONReader;
import com.bbstore.users.UserAuthenticator;

public class Main {
    public static void main(String[] args){
        JSONReader dbCredentials = new JSONReader("src/main/resources/db.json");
        DBConnection connection = new SQLConnection(
                dbCredentials.get("host"),
                dbCredentials.get("username"),
                dbCredentials.get("password")
        );

        Database db = new SQLDatabase();
        InputValidator inputValidator = new InputValidator();
        CookieManager cookieManager = new CookieManager();
        UserAuthenticator authenticator = new UserAuthenticator(db,cookieManager);

        BookBaeStoreApp bookBaeStoreApplication = new BookBaeStoreApp(
                connection,
                db,
                inputValidator,
                authenticator
        );

        //start the app
        bookBaeStoreApplication.execute();
    }
}
