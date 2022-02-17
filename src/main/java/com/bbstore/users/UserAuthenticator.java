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

    public boolean createNewAdmin(String firstName, String lastName, String email, String password, String type) throws Exception{
        String sql = "INSERT INTO admins VALUES('" +email+ "','" +firstName+ "','" +lastName+ "','" +password+ "','" +type+ "')";
        try {
            ResultSet createAdmin = this.database.executeQuery(
                    "SELECT * FROM orders"
//                    "INSERT INTO admins(email, fname, lname, password, type) VALUES('qweqw', 'qweqw', 'qweqwe', 'qwewew', '123212321')"
            );

            if (createAdmin.next()){
                System.out.println(createAdmin.getString("order_id"));
            }
        }catch(Exception e){
            throw new NewAdminCreationFailedException("New admin creation failed: "+e.getMessage());
        }
        return false;
    }

    public boolean login(String email, String password) throws Exception{
        try{
            ResultSet login = this.database.executeQuery(
                    "SELECT * FROM admins WHERE email='"+email+"' AND password='"+password+"'"
            );

            if (login.next()){
                String adminEmail = login.getString("email");
                setEmail(adminEmail);

                return true;
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
