package com.example;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {
	
	private static final Dotenv dEnv = Dotenv.load();
	
	private static final String URL = "jdbc:mysql://localhost:3306/itg_logintaskpagedb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String USER = "root";
	private static final String PASS = dEnv.get("DB_PASS");
	
	public static Connection getConn() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL,USER,PASS);
	}
}
