package com.bbstore.ui.uis;

import javax.swing.*;

public class AddBook extends JFrame {


    private JPanel addBook;
    private JTextField isbn;
    private JButton SAVEButton;
    private JButton CLEARButton;
    private JComboBox language;
    private JComboBox category;
    private JTextField author;
    private JTextField publisher;
    private JTextField year;
    private JTextField edition;
    private JTextField avqty;
    private JTextField price;
    private JTextField description;
    private JTextField bookname;

    public AddBook()  {

        setContentPane(addBook);
        setSize(1200,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void saveBook(){
        String Isbn = isbn.getText();
        String name=bookname.getText();


    }

    public static void main(String[] args) {
        AddBook addbook = new AddBook();
        addbook.setVisible(true);
    }


}
