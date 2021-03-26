/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BOOK_SERVLETS;

import Database.dbConn;
import POJO.Category;
import POJO.book;
import Validate.Book_checker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Charl3s
 */
public class Deletes extends HttpServlet {

    dbConn d = new dbConn();
    Connection conn;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            StringBuilder jb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                }
            } catch (IOException e) {
                /*report an error*/ }

            JSONObject jsonObject = new JSONObject(jb.toString());
            conn = d.getConnection();
            Book_checker bc = new Book_checker();
            String book_id = jsonObject.getString("book_id");

            book b = new book();
      
            b.setBook_id(book_id);
            

            JSONObject j = new JSONObject();
            if (bc.checkBook(book_id)) {
               
                String query = "delete from Book where book_id=?";
                PreparedStatement ps = conn.prepareStatement(query);

                // For the first parameter, 
                // get the data using request object 
                // sets the data to st pointer
                ps.setString(1, book_id);

                ps.executeUpdate();
                  j.put("status", 0);
                
                out.print(j);

            } else {
                j.put("status", 1);
                  j.put("description","doesnt exist");
                out.print(j);
            }
//               Gson g = new Gson();
//               String resp = g.toJson(b);
//               out.print(resp);

        } catch (Exception ex) {
            Logger.getLogger(Deletes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
