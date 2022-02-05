package com.bbstore.users;

import com.bbstore.database.Database;

import java.sql.ResultSet;

public class UserAuthenticator {
    private final Database database;
    public UserAuthenticator(Database database){
        this.database = database;
    }

    public void login(String email, String password) throws Exception{
        try{
            ResultSet login = this.database.executeQuery(
                    "SELECT * FROM admins WHERE email='"+email+"' AND password='"+password+"'"
            );

            if (login.next()){
                System.out.println(login.getString("email"));
                System.out.println(login.getString("password"));
            }else{
                throw new LogInFailedException("Incorrect email or password.");
            }
        }catch (Exception e){
            throw new LogInFailedException(e.getMessage());
        }
    }
    public void signOut(){

    }

    public boolean isLoggedIn(){
        return false;
    }
}
