/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BOOK_SERVLETS;

import Database.dbConn;
import POJO.book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Charl3s
 */
@WebServlet(name = "Fetch_book", urlPatterns = {"/Fetch_book"})
public class fetch_Books extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            dbConn d = new dbConn();
            Connection con;
            con = d.getConnection();
            response.setCharacterEncoding("UTF-8");

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
            JSONObject j = new JSONObject();
            JSONObject j1 = new JSONObject();

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            String book_id= jsonObject.getString("book_id");
           book b = new book();
            b.setBook_id(book_id);
            JSONObject jO = new JSONObject();
           
                String query = "SELECT Book.book_name,Book.book_author,Categories.Category_name from Book\n" +
"INNER JOIN Categories on Categories.Category_id=Book.book_Category_id WHERE Book.book_id=?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, b.getBook_id());
                ResultSet rs;
                rs = pstmt.executeQuery();
                List<JSONObject> resList = new ArrayList<>();
                try {
                    // get column names
                    ResultSetMetaData rsMeta = rs.getMetaData();
                    int columnCnt = rsMeta.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCnt; i++) {
                        columnNames.add(rsMeta.getColumnName(i).toUpperCase());
                    }

                    while (rs.next()) { // convert each object to an human readable JSON object
                        JSONObject obj = new JSONObject();
                        for (int i = 1; i <= columnCnt; i++) {
                            String key = columnNames.get(i - 1);
                            String value = rs.getString(i);
                            obj.put(key, value);
                        }

                        resList.add(obj);
                    }

                    jO.put("status", 0);
                    jO.put("data", resList);
                    out.print(jO);
                } catch (SQLException | JSONException se) {
                }
          
        } catch (SQLException ex) {
            Logger.getLogger(fetch_Books.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(fetch_Books.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }



