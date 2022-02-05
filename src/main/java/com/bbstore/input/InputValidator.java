package com.bbstore.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public boolean validateEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public boolean validatePassword(String password){
        return password.length() >= 8;
    }
}
