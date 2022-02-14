package com.bbstore.cookies;

import java.net.HttpCookie;

public class CookieManager {
    HttpCookie cookie;
    public CookieManager(){
        cookie = new HttpCookie("admin_email", "");
    }

    public void setEmail(String email){
        this.cookie.setValue(email);
    }
    public String getEmail(){
        return this.cookie.getValue();
    }
    public boolean isSet(){
        return !this.cookie.getValue().equals("");
    }
    public void clearEmail(){
        this.cookie.setValue("");
    }
}
