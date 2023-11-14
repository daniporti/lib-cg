package org.libcg.core;

import java.sql.*;

public class Persistence {
    private static Connection connection;
    
    private Persistence() {}
    
    static Connection getConnection() throws SQLException {
        if (Persistence.connection == null) {
            Persistence.connection = DriverManager.getConnection("jdbc:h2:~/lib_db");
        }
        
        return Persistence.connection;
    }
}
