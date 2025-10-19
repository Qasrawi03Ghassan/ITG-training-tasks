package com.example;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class homeServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
        	res.sendRedirect("index.html");
            return;
        }
        
        RequestDispatcher dp = req.getRequestDispatcher("/WEB-INF/home.html");
        dp.forward(req,res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		doPost(req,res);
	}
}
