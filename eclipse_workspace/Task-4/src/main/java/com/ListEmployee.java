package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class ListEmployee
 */
public class ListEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String line;
		String empType;
		try {
			BufferedReader bufferedReader = request.getReader();
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}

			line = sb.toString();
			// System.out.println(line);
			JSONParser jp = new JSONParser();
			JSONObject jo1 = new JSONObject();
			jo1 = (JSONObject) jp.parse(line);
			
			empType = jo1.get("empType").toString();
			System.out.println(empType);
			
			PrintWriter prt = response.getWriter();
			response.setContentType("application/json");
			
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/employee_database");
			Connection con = ds.getConnection();
			
			ListUser lu = new ListUser();
			prt.println(lu.listUser(empType, con));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
