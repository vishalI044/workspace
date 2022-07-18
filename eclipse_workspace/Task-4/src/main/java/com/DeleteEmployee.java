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
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String line,empType;
		int empCode;
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
			
			empCode = Integer.parseInt(jo1.get("empCode").toString());
			System.out.println(empCode);
			empType = jo1.get("empType").toString();
			System.out.println(empType);
			
			PrintWriter prt = response.getWriter();
			response.setContentType("application/json");
			
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/employee_database");
			Connection con = ds.getConnection();
			
			DeleteUser du = new DeleteUser();
			prt.println(du.deleteUser(empCode, empType, con));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
