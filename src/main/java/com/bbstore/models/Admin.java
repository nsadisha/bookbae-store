package com.bbstore.models;

import com.bbstore.database.Database;

import java.sql.ResultSet;

public class Admin {
    private final Database database;
    private final String email;
    private String name;
    private String type;

    public Admin(Database database, String email) throws Exception{
        this.database = database;
        this.email = email;
        setData();
    }

    //setters
    private void setData() throws Exception{
        ResultSet res = database.executeQuery("SELECT CONCAT(fname,' ',lname) AS 'name', type " +
                "FROM admins WHERE email='"+email+"'");

        if(res.next()){
            this.name = res.getString("name");
            this.type = res.getString("type");
        }
    }

    //getters
    public void remove() throws Exception{
        database.updateQuery(
                "DELETE FROM admins WHERE email='"+email+"'"
        );
    }
    public String getEmail(){
        return this.email;
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
}
