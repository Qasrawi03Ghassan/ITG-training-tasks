package com.example;

import java.io.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.sql.*;

@WebServlet("/api/user")
public class UsersServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            res.getWriter().write("{\"error\":\"Not logged in\"}");
            return;
        }
        
        String username = (String)session.getAttribute("username");
        
		
		PrintWriter out = res.getWriter();

        try (Connection con = DBConnection.getConn()) {
        	String sql = "SELECT user_id, username, email, phone_number,name FROM my_app_user WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            out.print("[");
            boolean first = true;
            while (rs.next()) {
                if (!first) out.print(",");
                out.print("{\"id\":" + rs.getInt("user_id") +
                          ",\"username\":\"" + rs.getString("username") +
                          "\",\"name\":\"" + rs.getString("name") +
                          "\",\"email\":\"" + rs.getString("email") +
                          "\",\"phone\":\"" + rs.getString("phone_number") + "\"}");
                first = false;
            }
            out.print("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
