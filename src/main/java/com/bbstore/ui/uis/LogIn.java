package com.bbstore.ui.uis;

import com.bbstore.alert.AlertBox;
import com.bbstore.input.InputValidator;
import com.bbstore.input.InvalidInputException;
import com.bbstore.input.PasswordEncryptor;
import com.bbstore.navigator.Navigator;
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
        setAutoRequestFocus(true);

        this.inputValidator = inputValidator;
        this.authenticator = authenticator;

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String passwordText = String.valueOf(passwordField.getPassword());

            try {
                login(email, passwordText);
            } catch(InvalidInputException exception){
                AlertBox.showAlert("Warning", exception.getMessage(), JOptionPane.WARNING_MESSAGE);
            } catch (Exception exception) {
                AlertBox.showAlert("Error", exception.getMessage(), JOptionPane.ERROR_MESSAGE);
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
            boolean loginSuccess = authenticator.login(email, password);

            if(loginSuccess){
                Navigator.push("home");
            }
        }
    }
}
