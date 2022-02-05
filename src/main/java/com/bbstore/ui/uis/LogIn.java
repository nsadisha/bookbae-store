package com.bbstore.ui.uis;

import com.bbstore.input.InputValidator;
import com.bbstore.input.InvalidInputException;
import com.bbstore.input.PasswordEncryptor;
import com.bbstore.ui.GUI;
import com.bbstore.users.UserAuthenticator;

import javax.swing.*;

public class LogIn extends GUI {
    private JPanel loginPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn;

    private final InputValidator inputValidator;
    private final UserAuthenticator authenticator;

    public LogIn(UserAuthenticator authenticator, InputValidator inputValidator){
        setSize(400, 400);
        setTitle("BookBae Store - Login");
        setContentPane(this.loginPanel);
        setResizable(false);
        setLocationRelativeTo(null);

        this.inputValidator = inputValidator;
        this.authenticator = authenticator;

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String passwordText = String.valueOf(passwordField.getPassword());

            try {
                login(email, passwordText);
            } catch (Exception exception) {
                System.out.println("Error: "+exception.getMessage());
            }

        });

    }

    private void login(String email, String passwordText) throws Exception{
        if(!inputValidator.validateEmail(email)) {
            throw new InvalidInputException("Invalid email address");
        }else if(!inputValidator.validatePassword(passwordText)){
            throw new InvalidInputException("Invalid password!");
        }else{
            String password = PasswordEncryptor.md5(passwordText);
            authenticator.login(email, password);
        }
    }
}
