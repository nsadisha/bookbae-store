package com.bbstore.ui.uis;

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
        int count=0;
        try{
            ResultSet res = getOrders();
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
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private ResultSet getOrders() throws Exception{
        return this.database.executeQuery("SELECT * FROM orders");
    }
}
