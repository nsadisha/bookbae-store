package com.bbstore.users;

import com.bbstore.cookies.CookieManager;
import com.bbstore.database.Database;

import java.sql.ResultSet;

public class UserAuthenticator {
    private final Database database;
    private final CookieManager cookieManager;

    public UserAuthenticator(Database database, CookieManager cookieManager){
        this.database = database;
        this.cookieManager = cookieManager;
    }

    public void login(String email, String password) throws Exception{
        try{
            ResultSet login = this.database.executeQuery(
                    "SELECT * FROM admins WHERE email='"+email+"' AND password='"+password+"'"
            );

            if (login.next()){
                String adminEmail = login.getString("email");

                setEmail(adminEmail);

                System.out.println(isLoggedIn());
                System.out.println(getSignedEmail());
            }else{
                throw new LogInFailedException("Incorrect email or password.");
            }
        }catch (Exception e){
            throw new LogInFailedException(e.getMessage());
        }
    }
    public void setEmail(String email){
        this.cookieManager.setEmail(email);
    }
    public void signOut(){
        this.cookieManager.clearEmail();
    }

    public boolean isLoggedIn(){
        return this.cookieManager.isSet();
    }

    public String getSignedEmail(){
        return this.cookieManager.getEmail();
    }
}
