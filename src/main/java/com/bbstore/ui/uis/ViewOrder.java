package com.bbstore.ui.uis;

import com.bbstore.navigator.Navigator;
import com.bbstore.order.Order;
import com.bbstore.ui.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewOrder extends GUI {

    private JPanel orderPane;
    private JLabel orderIdField;
    private JLabel totalPrice;
    private JLabel orderTime;
    private JLabel orderNote;
    private JLabel orderAddress;
    private JTable items;
    private JButton completeButton;

    private final Order order;

    public ViewOrder(Order order){
        setSize(900, 600);
        setTitle("View Order");
        setContentPane(this.orderPane);
        setResizable(false);
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.order = order;
        this.completeButton.setVisible(!order.isCompleted());

        this.completeButton.addActionListener(e -> {
            order.complete();
            Navigator.openPopUp(new Orders(order.getDatabase()));
        });
    }

    @Override
    public void initState(){
        super.initState();
        this.orderIdField.setText("Order - #"+order.getOrderId());
        this.totalPrice.setText(order.getOrderTotalPrice());
        this.orderTime.setText(order.getOrderDate());
        this.orderNote.setText(order.getOrderNote());
        this.orderAddress.setText(order.getAddress());
        createTable();
    }
    @Override
    public void onDispose() {
        super.onDispose();
        Navigator.openPopUp(new Orders(order.getDatabase()));
    }

    private void createTable(){
        this.items.setModel(new DefaultTableModel(
                order.getItemData(),
                order.getHeaders()
        ));
    }
}
