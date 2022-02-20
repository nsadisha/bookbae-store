package com.bbstore.alert;

import javax.swing.*;

public abstract class AlertBox {

    public static void showAlert(String title, String message, int type){

        JOptionPane.showMessageDialog(null,message,title, type);
    }
}
