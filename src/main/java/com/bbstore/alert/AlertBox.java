package com.bbstore.alert;

import javax.swing.*;

public abstract class AlertBox {

    public static void showAlert(String message,String type){

        JOptionPane.showMessageDialog(null,message,type,JOptionPane.INFORMATION_MESSAGE);

    }
}
