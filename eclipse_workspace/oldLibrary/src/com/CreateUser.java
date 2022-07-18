package com;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateUser {
public static JSONObject CreateUser(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		
		Connection con = DBConnection.initialise();
		StringBuffer sb = new StringBuffer();
		ResultSet rs;
		String line;
		String userName, password, name, empType = null;
		int empCode, salary;
		
		/*JSON format input
		{
			"empCode": 101,
			"userName": "vishal123",
			"password": "vis@123",
			"name": "vishal",
			"salary":20000,
			"empType": "contract"
		}
		*/
		
		JSONObject jo = new JSONObject();
		PreparedStatement st = null;
		try {
			BufferedReader bufferedReader = request.getReader();
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			
			line = sb.toString();
			//System.out.println(line);
			JSONParser jp = new JSONParser();
			JSONObject jo1 = new JSONObject();
			jo1 = (JSONObject) jp.parse(line);
			
			userName = jo1.get("userName").toString();
			password = jo1.get("password").toString();
			name = jo1.get("name").toString();
			empType = jo1.get("empType").toString();
			
			empCode = Integer.parseInt(jo1.get("empCode").toString());
			salary = Integer.parseInt(jo1.get("salary").toString());
			
			if(empType.equals("permanent")) {
				String insertQuery = "INSERT INTO Permanent_Employee VALUES(?,?,?,?,?)";
				st = con.prepareStatement(insertQuery);
				st.setInt(1, empCode);
				st.setString(2, userName);
				st.setString(3, password);
				st.setString(4, name);
				st.setInt(5, salary);
				st.setString(6, empType);
			}else if(empType.equals("partTime")) {
				String insertQuery = "INSERT INTO partTime_Employee VALUES(?,?,?,?,?)";
				st = con.prepareStatement(insertQuery);
				st.setInt(1, empCode);
				st.setString(2, userName);
				st.setString(3, password);
				st.setString(4, name);
				st.setInt(5, salary);
				st.setString(6, empType);
			}else if(empType.equals("contract")) {
				String insertQuery = "INSERT INTO Contract_Employee VALUES(?,?,?,?,?)";
				st = con.prepareStatement(insertQuery);
				st.setInt(1, empCode);
				st.setString(2, userName);
				st.setString(3, password);
				st.setString(4, name);
				st.setInt(5, salary);
				st.setString(6, empType);
			}else {
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		if(empType.equals("permanent")) {
			String insertQuery = "SELECT * FROM employee_database.permanent_employee;";
			st = con.prepareStatement(insertQuery);
			rs = st.executeQuery();
			while(rs!=null) {
				jo.put("empCode", rs.getInt("empCode"));
				jo.put("empType", rs.getInt("empType"));
				jo.put("userName", rs.getInt("userName"));
				jo.put("password", rs.getInt("password"));
				jo.put("salary", rs.getInt("salary"));
				jo.put("name", rs.getInt("name"));
			}
			return jo;
		}else if(empType.equals("Contract")) {
			String insertQuery = "SELECT * FROM employee_database.contract_employee;";
			st = con.prepareStatement(insertQuery);
			rs = st.executeQuery();
			while(rs!=null) {
				jo.put("empCode", rs.getInt("empCode"));
				jo.put("empType", rs.getInt("empType"));
				jo.put("userName", rs.getInt("userName"));
				jo.put("password", rs.getInt("password"));
				jo.put("salary", rs.getInt("salary"));
				jo.put("name", rs.getInt("name"));
			}
			return jo;
		}else if(empType.equals("partTime")) {
			String insertQuery = "SELECT * FROM employee_database.partTime_employee WH;";
			st = con.prepareStatement(insertQuery);
			rs = st.executeQuery();
			while(rs!=null) {
				jo.put("empCode", rs.getInt("empCode"));
				jo.put("empType", rs.getInt("empType"));
				jo.put("userName", rs.getInt("userName"));
				jo.put("password", rs.getInt("password"));
				jo.put("salary", rs.getInt("salary"));
				jo.put("name", rs.getInt("name"));
			}
			return jo;
		}
		st.close();
		con.close();
			return jo;
		}
		
	}
}
