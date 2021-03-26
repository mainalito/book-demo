/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category_servlet;

import Database.dbConn;
import POJO.Category;
import com.google.gson.Gson;
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
import org.json.JSONArray;
import org.json.JSONObject;
import Validate.category;

/**
 *
 * @author Charl3s
 */
public class addCategory extends HttpServlet {

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
            String category_id = jsonObject.getString("category_id");
            String category_name = jsonObject.getString("category_name");
            Category c = new Category();
            c.setCategory_id(category_id);
            c.setCategory_name(category_name);
            JSONObject j = new JSONObject();
            category cv = new category();
            String query = "insert into Categories (Category_id,Category_name)VALUES (?,?)";
            if (!cv.checkCategory(category_id)) {
                PreparedStatement ps = conn.prepareStatement(query);

                // For the first parameter, 
                // get the data using request object 
                // sets the data to st pointer
                ps.setString(1, c.getCategory_id());
                ps.setString(2, c.getCategory_name());

                ps.executeUpdate();

                j.put("status", 0);
                out.print(j);
            } else {
                j.put("status", 1);
                out.print(j);
            }
//               Gson g = new Gson();
//               String resp = g.toJson(b);
//               out.print(resp);

        } catch (Exception ex) {
            Logger.getLogger(addCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
