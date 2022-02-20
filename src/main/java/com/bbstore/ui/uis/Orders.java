package com.bbstore.ui.uis;

import com.bbstore.alert.AlertBox;
import com.bbstore.components.OrderTile;
import com.bbstore.database.Database;
import com.bbstore.ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Orders extends GUI {

    private JPanel ordersPanel;
    private JPanel orders;

    private final Database database;

    public Orders(Database database){
        setSize(900, 600);
        setTitle("All Orders");
        setContentPane(this.ordersPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.database = database;
    }

    @Override
    protected void initState(){
        super.initState();
        getOrders();

    }
    private void getOrders(){
        int count=0;
        try{
            setAlwaysOnTop(false);
            ResultSet res = this.database.executeQuery("SELECT * FROM orders");
            while (res.next()){
                String orderId = res.getString("order_id");
                String status = res.getString("status");
                //insert new order items
                orders.add(new OrderTile(orderId, status, database));
                //count orders
                count++;
            }

            orders.setLayout(new FlowLayout());
            this.orders.setPreferredSize(new Dimension(750, 66*count));
        }catch (Exception e){
            AlertBox.showAlert("Cannot load orders", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }finally {
            setAlwaysOnTop(true);
        }
    }
}
