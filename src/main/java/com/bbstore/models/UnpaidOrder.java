package com.bbstore.models;

import com.bbstore.database.Database;

import java.sql.ResultSet;

public class UnpaidOrder {
    private final Database database;
    private final String orderId;
    private String email;
    private String price;

    public UnpaidOrder(Database database, String orderId) throws Exception{
        this.database = database;
        this.orderId = orderId;
        setData();
    }

    private void setData() throws Exception{
        ResultSet res = database.executeQuery(
                "SELECT email, total_price FROM placed_orders WHERE order_id='"+this.orderId+"'"
        );

        if(res.next()){
            this.email = res.getString("email");
            this.price = res.getString("total_price");
        }
    }

    public void remove() throws Exception{
        this.database.updateQuery(
                "DELETE FROM placed_orders WHERE order_id='"+orderId+"'"
        );
    }

    //getters
    public String getOrderId(){
        return this.orderId;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPrice(){
        return this.price;
    }
}
