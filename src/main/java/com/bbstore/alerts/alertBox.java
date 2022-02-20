package com.bbstore.alerts;

import javax.swing.*;

public class alertBox {

    public alertBox(String message,String type){

        JOptionPane.showMessageDialog(null,message,type,JOptionPane.INFORMATION_MESSAGE);
    }

}
