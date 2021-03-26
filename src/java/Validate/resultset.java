/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import POJO.book;
import Database.dbConn;

/**
 *
 * @author Charl3s
 */
public class resultset {
 String query= "select book_name,book_author from Book ";
  public  List<book> getBooks() throws Exception {
        List<book> results = new ArrayList<>();

        try {
            dbConn d = new dbConn();
            Connection con;
            con = d.getConnection();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {

               book bs = new book();
                 
               bs.setBook_title(rs.getString("book_name"));
               bs.setBook_author(rs.getString("book_author"));
              

                results.add(bs);

            }

        } catch (SQLException e) {

        }
        // log any exceptions ...
        return results;
    }
}
