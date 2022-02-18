package com.bbstore.ui.uis;

import com.bbstore.input.InputValidator;
import com.bbstore.input.PasswordEncryptor;
import com.bbstore.navigator.Navigator;
import com.bbstore.ui.GUI;
import com.bbstore.users.NewAdminCreationFailedException;
import com.bbstore.users.UserAuthenticator;

import javax.swing.*;

public class NewAdmin extends GUI {

    private JPanel newAdmin;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton addButton;
    private JButton cancelButton;
    private JRadioButton adminField;
    private JRadioButton superAdminField;
    private String adminType;

    private final UserAuthenticator authenticator;
    private final InputValidator inputValidator;

    public NewAdmin(UserAuthenticator authenticator, InputValidator inputValidator){
        setSize(500, 450);
        setTitle("Add New Admin");
        setContentPane(this.newAdmin);
        setResizable(false);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.adminType = "admin";

        this.authenticator = authenticator;
        this.inputValidator = inputValidator;

        adminField.addActionListener(e -> {
            adminField.setSelected(true);
            superAdminField.setSelected(false);
            adminType = "admin";
        });
        superAdminField.addActionListener(e -> {
            superAdminField.setSelected(true);
            adminField.setSelected(false);
            adminType = "super";
        });

        cancelButton.addActionListener(e -> Navigator.pop());
        addButton.addActionListener(e -> {
            String firstname = firstNameField.getText();
            String lastname = lastNameField.getText();
            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            boolean isNewAdminCreated = false;
            try {
                isNewAdminCreated = addNewAdmin(email, firstname, lastname, password, confirmPassword, adminType);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            if(isNewAdminCreated){
                Navigator.pop();
            }
        });
    }

    private boolean addNewAdmin(String email, String firstName,
                                String lastName, String password, String confirmPassword, String type) throws Exception{
        if(!inputValidator.validateField(firstName)){
            throw new NewAdminCreationFailedException("First name must contain at least 1 character!");
        }else if(!inputValidator.validateField(lastName)){
            throw new NewAdminCreationFailedException("Last name must contain at least 1 character!");
        }else if(!inputValidator.validateEmail(email)){
            throw new NewAdminCreationFailedException("Invalid email address!");
        }else if(!inputValidator.validatePassword(password)){
            throw new NewAdminCreationFailedException("Invalid password(use at least 8 characters)!");
        }else if(!inputValidator.confirmPassword(password, confirmPassword)){
            throw new NewAdminCreationFailedException("Password mismatch!");
        }else{
            String _password = PasswordEncryptor.md5(password);
            return authenticator.createNewAdmin(
                    firstName,
                    lastName,
                    email,
                    _password,
                    type
            );
        }
    }
}
