package com.bbstore.database;

import java.sql.*;

public class SQLDatabase implements Database{

    Connection connection;
    Statement statement;

    public void connect(Connection connection){
        this.connection = connection;
    }

    //Execute a SQL query
    public ResultSet executeQuery(String query) throws QueryExecutionFailedException {
        try {

            //creating a statement
            this.statement = this.connection.createStatement();
            //Return the result
            return this.statement.executeQuery(query);

        } catch (SQLException e) {
            throw new QueryExecutionFailedException("SQL database query execution failed");
        }
    }
    public int updateQuery(String query) throws Exception {
        try {

            //creating a statement
            this.statement = this.connection.createStatement();
            //Return the result
            return this.statement.executeUpdate(query);

        }catch (SQLIntegrityConstraintViolationException e){
            throw new SQLIntegrityConstraintViolationException(e.getMessage());
        }catch (SQLException e) {
            e.printStackTrace();
            throw new QueryExecutionFailedException("SQL database update query execution failed("+e.getMessage()+")");
        }
    }

}