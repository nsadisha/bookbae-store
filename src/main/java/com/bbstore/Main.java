package com.bbstore;

import com.bbstore.connection.DBConnection;
import com.bbstore.connection.SQLConnection;
import com.bbstore.cookies.CookieManager;
import com.bbstore.database.Database;
import com.bbstore.database.SQLDatabase;
import com.bbstore.input.InputValidator;
import com.bbstore.navigator.Navigator;
import com.bbstore.reader.JSONReader;
import com.bbstore.ui.GUI;
import com.bbstore.ui.uis.*;
import com.bbstore.users.UserAuthenticator;

import java.util.HashMap;

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

        //Navigator routes
        HashMap<String, GUI> routes = new HashMap<>();
        routes.put("login", new LogIn(authenticator, inputValidator));
        routes.put("home", new Dashboard(authenticator, db));
        routes.put("newAdmin", new NewAdmin(authenticator, inputValidator));
        routes.put("orders", new Orders(db));

        Navigator.setRoutes(routes);

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
