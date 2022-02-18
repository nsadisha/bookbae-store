package com.bbstore.database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Database {

    void connect(Connection connection);
    ResultSet executeQuery(String query) throws QueryExecutionFailedException;
    int updateQuery(String query) throws Exception;
}
