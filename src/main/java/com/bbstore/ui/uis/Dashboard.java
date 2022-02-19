package com.bbstore.ui.uis;

import com.bbstore.components.AdminTile;
import com.bbstore.components.UnpaidOrderTile;
import com.bbstore.models.DashboardData;
import com.bbstore.navigator.Navigator;
import com.bbstore.ui.GUI;
import com.bbstore.users.UserAuthenticator;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends GUI {
    private JPanel homePanel;
    private JLabel netIncome;
    private JLabel totalOrders;
    private JLabel totalUsers;
    private JLabel totalBooks;
    private JLabel adminName;

    private JButton newAdminBtn;
    private JButton viewOrdersBtn;
    private JButton newBookBtn;
    private JButton editBookBtn;
    private JButton signOutBtn;

    private JPanel allAdmins;
    private JPanel unpaidOrders;

    private final UserAuthenticator authenticator;
    private final DashboardData data;

    public Dashboard(DashboardData data) {
        setSize(1000, 700);
        setExtendedState(GUI.MAXIMIZED_BOTH);
        setTitle("BookBae Store - Dashboard");
        setContentPane(this.homePanel);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        this.authenticator = data.getAuthenticator();
        this.data = data;

        //action listeners
        signOutBtn.addActionListener(e -> this.signOut());
        newAdminBtn.addActionListener(e -> Navigator.openPopUp("newAdmin"));
        viewOrdersBtn.addActionListener(e -> Navigator.openPopUp("orders"));
        editBookBtn.addActionListener(e -> Navigator.openPopUp("search"));
        newBookBtn.addActionListener(e -> Navigator.openPopUp("newBook"));
    }
    @Override
    protected void initState(){
        try{
            String name = "Welcome "+authenticator.getAdminName()+"!";
            this.adminName.setText(name);

            String netIncome = data.getNetIncome();
            this.netIncome.setText(netIncome);

            String totalUserCount = data.getUserCount();
            this.totalUsers.setText(totalUserCount);

            String totalOrderCount = data.getOrderCount();
            this.totalOrders.setText(totalOrderCount);

            String totalBookCount = data.getBookCount();
            this.totalBooks.setText(totalBookCount);

            //add admin to list
            AdminTile[] admins = data.getOtherAdmins();
            allAdmins.setPreferredSize(new Dimension(-1, admins.length*60));
            for(AdminTile admin : admins){
                this.allAdmins.add(admin);
            }
            //add unpaid orders
            UnpaidOrderTile[] unpaidOrderArr = data.getUnpaidOrders();
            unpaidOrders.setPreferredSize(new Dimension(-1, unpaidOrderArr.length*60));
            for(UnpaidOrderTile order : unpaidOrderArr){
                this.unpaidOrders.add(order);
            }
        }catch (Exception e){
            System.out.println("Error: Dashboard query execution failed.\n("+e.getMessage()+")");
        }
    }

    private void signOut(){
        authenticator.signOut();
        Navigator.push("login");
    }
}
