package com.bbstore.models;

import com.bbstore.components.AdminTile;
import com.bbstore.components.UnpaidOrderTile;
import com.bbstore.database.Database;
import com.bbstore.users.UserAuthenticator;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DashboardData {
    private final DecimalFormat df;
    private final Database database;
    private final UserAuthenticator authenticator;

    public DashboardData(Database database, UserAuthenticator authenticator){
        this.database = database;
        this.authenticator = authenticator;
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
    public UserAuthenticator getAuthenticator(){
        return this.authenticator;
    }
    public AdminTile[] getOtherAdmins() throws Exception{
        ResultSet res = database.executeQuery(
                "SELECT email FROM admins WHERE email!='"+this.authenticator.getSignedEmail()+"'"
        );
        List<AdminTile> adminTileList = new ArrayList<>();
        int i = 0;
        while(res.next()){
            adminTileList.add(
                    new AdminTile(
                            new Admin(database, res.getString("email")),
                            this.authenticator
                    )
            );
            i++;
        }
        AdminTile[] admins = new AdminTile[i];
        for(int j=0; j<admins.length; j++){
            admins[j] = adminTileList.get(j);
        }

        return admins;
    }
    public UnpaidOrderTile[] getUnpaidOrders() throws Exception {
        ResultSet res = database.executeQuery(
                "SELECT order_id FROM placed_orders"
        );
        List<UnpaidOrderTile> unpaidOrderTiles = new ArrayList<>();
        int i = 0;
        while(res.next()){
            unpaidOrderTiles.add(
                    new UnpaidOrderTile(
                            new UnpaidOrder(database, res.getString("order_id"))
                    )
            );
            i++;
        }
        UnpaidOrderTile[] orders = new UnpaidOrderTile[i];
        for(int j=0; j<orders.length; j++){
            orders[j] = unpaidOrderTiles.get(j);
        }

        return orders;
    }
}
