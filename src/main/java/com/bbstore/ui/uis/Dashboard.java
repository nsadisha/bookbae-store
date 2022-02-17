package com.bbstore.ui.uis;

import com.bbstore.database.Database;
import com.bbstore.navigator.Navigator;
import com.bbstore.ui.GUI;
import com.bbstore.users.UserAuthenticator;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class Dashboard extends GUI {
    private JPanel homePanel;
    private JLabel netIncome;
    private JLabel totalOrders;
    private JLabel totalUsers;
    private JLabel totalBooks;

    private JButton newAdminBtn;
    private JButton viewOrdersBtn;
    private JButton newBookBtn;
    private JButton editBookBtn;
    private JButton signOutBtn;

    private JScrollPane allAdmins;
    private JScrollPane unpaidOrders;

    private final UserAuthenticator authenticator;
    private final Database database;
    private final DecimalFormat df;

    public Dashboard(UserAuthenticator authenticator, Database database) {
        setSize(1000, 700);
        setExtendedState(GUI.MAXIMIZED_BOTH);
        setTitle("BookBae Store - Dashboard");
        setContentPane(this.homePanel);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        this.authenticator = authenticator;
        this.database = database;
        this.df = new DecimalFormat();

        // init screen
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    initScreen();
                } catch (Exception exception) {
                    System.out.println("dashboard initialization failed.\nMsg: "+exception.getMessage());
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        //action listeners
        signOutBtn.addActionListener(e -> this.signOut());
        newAdminBtn.addActionListener(e ->{

        });
        viewOrdersBtn.addActionListener(e ->{

        });
        editBookBtn.addActionListener(e -> {

        });
        newBookBtn.addActionListener(e -> {

        });
    }

    private void initScreen() throws Exception{
        try{
            String netIncome = this.getNetIncome();
            this.netIncome.setText(netIncome);

            String totalUserCount = this.getUserCount();
            this.totalUsers.setText(totalUserCount);

            String totalOrderCount = this.getOrderCount();
            this.totalOrders.setText(totalOrderCount);

            String totalBookCount = this.getBookCount();
            this.totalBooks.setText(totalBookCount);
        }catch (Exception e){
            throw new Exception("Error: Dashboard query execution failed.\n("+e.getMessage()+")");
        }
    }
    private String getNetIncome() throws Exception{
        df.applyPattern("Rs: ###,###,###.00");

        ResultSet res = database.executeQuery("SELECT SUM(total_price) 'total' FROM orders");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("total")));
        }
        return null;
    }
    private String getUserCount() throws Exception{
        df.applyPattern("###,###,###");

        ResultSet res = database.executeQuery("SELECT COUNT(*) 'count' FROM users");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("count")));
        }
        return null;
    }
    private String getOrderCount() throws Exception{
        df.applyPattern("###,###,###");

        ResultSet res = database.executeQuery("SELECT COUNT(*) 'count' FROM orders");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("count")));
        }
        return null;
    }
    private String getBookCount() throws Exception{
        df.applyPattern("###,###,###");

        ResultSet res = database.executeQuery("SELECT COUNT(*) 'count' FROM books");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("count")));
        }
        return null;
    }


    private void signOut(){
        authenticator.signOut();
        Navigator.push("login");
    }
}
