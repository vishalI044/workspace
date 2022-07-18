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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String line;
		String userName, password, name, empType;
		int empCode, salary;
		try {
			BufferedReader bufferedReader = request.getReader();
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}

			line = sb.toString();
			// System.out.println(line);
			JSONParser jp = new JSONParser();
			JSONObject jo = new JSONObject();
			jo = (JSONObject) jp.parse(line);

			userName = jo.get("userName").toString();
			password = jo.get("password").toString();
			name = jo.get("name").toString();
			empType = jo.get("empType").toString();

			empCode = Integer.parseInt(jo.get("empCode").toString());
			salary = Integer.parseInt(jo.get("salary").toString());
			System.out.println(userName + " " + password + " " + empCode + " " + salary + " " + name + " " + empType);

			PrintWriter prt = response.getWriter();
			response.setContentType("application/json");
			
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/employee_database");
			Connection con = ds.getConnection();
			
			CreateUser cu = new CreateUser();
			prt.println(cu.CreateUser(empCode, userName, password, name, salary, empType, con));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
