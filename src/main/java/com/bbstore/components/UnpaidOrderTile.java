package com.bbstore.components;

import com.bbstore.alert.AlertBox;
import com.bbstore.models.UnpaidOrder;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class UnpaidOrderTile extends JPanel{
    private JPanel tile;
    private JLabel orderId;
    private JLabel orderTotal;
    private JButton removeButton;

    public UnpaidOrderTile(UnpaidOrder order) {
        this.tile.setPreferredSize(new Dimension(548, 40));
        add(tile);
        this.orderId.setText("#"+order.getOrderId());
        DecimalFormat df = new DecimalFormat("Rs: ###,###,###.00");
        this.orderTotal.setText(
                order.getEmail()+" - "+df.format(Double.parseDouble(order.getPrice()))
        );

        removeButton.addActionListener(e -> {
            try {
                order.remove();
                this.tile.setVisible(false);
            } catch (Exception exception) {
                AlertBox.showAlert("Remove order failed", exception.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
