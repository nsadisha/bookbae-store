package com.bbstore.users;

import com.bbstore.cookies.CookieManager;
import com.bbstore.database.Database;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserAuthenticator {
    private final Database database;
    private final CookieManager cookieManager;

    public UserAuthenticator(Database database, CookieManager cookieManager){
        this.database = database;
        this.cookieManager = cookieManager;
    }

    public boolean createNewAdmin(String firstName, String lastName,
                                  String email, String password, String type) throws Exception{
        String sql = "INSERT INTO admins VALUES('" +email+ "','" +firstName+ "','"
                +lastName+ "','" +password+ "','" +type+ "')";
        try {
            int createAdmin = this.database.updateQuery(sql);

            return createAdmin==1;
        }catch(SQLIntegrityConstraintViolationException e){
            throw new NewAdminCreationFailedException("Email is already in use. Use a different email address.");
        }catch(Exception e){
            throw new NewAdminCreationFailedException("New admin creation failed: "+e.getMessage());
        }
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
    public String getAdminName(){
        try {
            ResultSet res = database.executeQuery(
                    "SELECT CONCAT(fname,' ', lname) AS 'name' FROM admins WHERE email='"+getSignedEmail()+"'"
            );
            if(res.next()){
                return res.getString("name");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    public String getAdminType(){
        try {
            ResultSet res = database.executeQuery(
                    "SELECT type FROM admins WHERE email='"+getSignedEmail()+"'"
            );
            if(res.next()){
                return res.getString("type");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
}
