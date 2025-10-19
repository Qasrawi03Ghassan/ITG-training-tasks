package com.example;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res ) throws IOException,ServletException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			Connection con = DBConnection.getConn();
			String sql = "SELECT * FROM itg_logintaskpagedb.my_app_user WHERE username = ?";
			PreparedStatement stmnt = con.prepareStatement(sql);
			stmnt.setString(1, username);
			ResultSet rs = stmnt.executeQuery();
			
			res.setContentType("text/html");
			
			if (rs.next()) {
                String storedHash = rs.getString("password");

                if (BCrypt.checkpw(password, storedHash)) {
                	HttpSession session = req.getSession();
                	session.setAttribute("username", username);
                	
                	RequestDispatcher dp = req.getRequestDispatcher("/WEB-INF/home.html");
                	dp.forward(req, res);
                    return;
                } else {
                	res.sendRedirect("index.html?error=true");
                    return;
                }

            } else {
            	res.sendRedirect("index.html?error=true");
                return;
            }
			
		}catch(Exception e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}
}
