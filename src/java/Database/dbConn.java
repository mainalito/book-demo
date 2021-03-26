/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Charl3s
 */
public class dbConn {
    public Connection getConnection()throws Exception{
        try 
        {
            // TODO code application logic here
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //registering the driver

            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=book";
            String user = "sa";
            String pass = "123456789";
            Connection conn;
            conn = DriverManager.getConnection(dbURL, user, pass);
            return conn;
        }
       catch(ClassNotFoundException | SQLException e){
            throw e;
        }
     }
}
