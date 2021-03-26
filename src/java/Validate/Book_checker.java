/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import Database.dbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Charl3s
 */
public class Book_checker{
     public  boolean checkBook(String book_id) throws Exception {
        boolean st = false;
        try {
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            String Query = "Select  * from Book WHERE book_id=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, book_id);
           
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception e) {
        }
        return st;
    }
}
