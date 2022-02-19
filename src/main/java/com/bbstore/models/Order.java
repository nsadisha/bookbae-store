package com.bbstore.models;

import com.bbstore.database.Database;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {
    private final DecimalFormat df = new DecimalFormat();
    private final String[] tableHeaders = new String[]{"NAME", "ISBN", "PRICE", "QTY", "TOTAL"};
    private Object[][] itemData;
    private final Database database;
    private String email;
    private final String id;
    private String total;
    private String date;
    private String note;
    private String address;
    private String status;

    public Order(Database database, String id){
        df.applyPattern("Rs: ###,###,###.00");
        this.database = database;
        this.id = id;

        //initialize order
        init();
    }

    //setters
    public void init(){
        try{
            setTableData();
            setOrderData();
            setAddress();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void setOrderData() throws Exception{
        String sql = "SELECT * FROM orders WHERE order_id='"+id+"'";
        ResultSet res = database.executeQuery(sql);

        if(res.next()){
            this.email = res.getString("email");
            this.total = res.getString("total_price");
            this.note = res.getString("note");
            this.date = res.getString("date");
            this.status = res.getString("status");
        }
    }
    private void setAddress() throws Exception{
        String sql = "SELECT Concat(line1, ', ', line2, ',\n', city, ',\n', province, '\n', zip) AS address " +
                "FROM user_addresses WHERE email='"+email+"'";

        ResultSet res = database.executeQuery(sql);
        if(res.next()){
            this.address = res.getString("address");
        }
    }
    private void setTableData() throws Exception{
        String sql = "SELECT B.name, B.isbn, B.price, O.quantity, O.quantity * B.price 'total' " +
                "FROM order_items O RIGHT OUTER JOIN books B ON O.isbn=B.isbn WHERE O.order_id='"+id+"'";

        ResultSet res = database.executeQuery(sql);
        ArrayList<String[]> itemDataList = new ArrayList<>();
        int i = 0;
        while(res.next()){
            itemDataList.add(new String[]{
                    res.getString("name"),
                    res.getString("isbn"),
                    df.format(Double.parseDouble(res.getString("price"))),
                    res.getString("quantity"),
                    df.format(Double.parseDouble(res.getString("total"))),
            });
            i++;
        }
        //init data object array
        this.itemData = new Object[i][];
        for(int j = 0; j<itemData.length; j++){
            itemData[j] = itemDataList.get(j);
        }
    }
    public void complete(){
        String sql = "UPDATE orders SET status='Shipped' WHERE order_id='"+id+"'";
        try {
            this.database.updateQuery(sql);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    //getters
    public Database getDatabase() {
        return database;
    }
    public String getOrderId(){
        return this.id;
    }
    public String getOrderTotalPrice(){
        return df.format(Double.parseDouble(this.total));
    }
    public String getOrderDate(){
        return  "On "+this.date.split(" ")[0]+" at "+this.date.split(" ")[1];
    }
    public String getOrderNote(){
        if(this.note.isEmpty()){
            return "No order notes!";
        }
        return this.note;
    }
    public String getAddress(){
        return this.address;
    }
    public boolean isCompleted(){
        return !this.status.equalsIgnoreCase("processing");
    }
    public String[] getHeaders(){
        return this.tableHeaders;
    }
    public Object[][] getItemData(){
        return this.itemData;
    }
}
