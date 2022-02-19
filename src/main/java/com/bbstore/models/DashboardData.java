package com.bbstore.models;

import com.bbstore.database.Database;

import java.sql.ResultSet;
import java.text.DecimalFormat;

public class DashboardData {
    private final DecimalFormat df;
    private final Database database;

    public DashboardData(Database database){
        this.database = database;
        this.df = new DecimalFormat();
    }

    public String getNetIncome() throws Exception{
        df.applyPattern("Rs: ###,###,###.00");

        ResultSet res = database.executeQuery("SELECT SUM(total_price) 'total' FROM orders");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("total")));
        }
        return null;
    }
    public String getUserCount() throws Exception{
        df.applyPattern("###,###,###");

        ResultSet res = database.executeQuery("SELECT COUNT(*) 'count' FROM users");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("count")));
        }
        return null;
    }
    public String getOrderCount() throws Exception{
        df.applyPattern("###,###,###");

        ResultSet res = database.executeQuery("SELECT COUNT(*) 'count' FROM orders");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("count")));
        }
        return null;
    }
    public String getBookCount() throws Exception{
        df.applyPattern("###,###,###");

        ResultSet res = database.executeQuery("SELECT COUNT(*) 'count' FROM books");
        if(res.next()){
            return df.format(Double.valueOf(res.getString("count")));
        }
        return null;
    }
}
