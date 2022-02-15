package com.bbstore.ui.uis;

import javax.swing.*;

public class AddBook extends JFrame {


    private JPanel addBook;
    private JTextField textField1;
    private JTextField textField2;
    private JButton SAVEButton;
    private JButton CLEARButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public AddBook()  {

        setContentPane(addBook);
        setSize(1200,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        AddBook addbook = new AddBook();
        addbook.setVisible(true);
    }
}
