package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class CreateUser {
	public static String CreateUser(int empCode, String userName, String password, String name, int salary,
			String empType, Connection con) throws ClassNotFoundException, SQLException {
		
		//Connection con = DBConnection.initialise();
		PreparedStatement st = null;
		ResultSet rs;
		if(empType.equals("permanent")) {
			String insertQuery = "INSERT INTO permanent_employee VALUES(?,?,?,?,?,?)";
			st = con.prepareStatement(insertQuery);
			st.setInt(1, empCode);
			st.setString(2, userName);
			st.setString(3, password);
			st.setString(4, name);
			st.setInt(5, salary);
			st.setString(6, empType);
			st.executeUpdate();
			
		}else if(empType.equals("partTime")) {
			String insertQuery = "INSERT INTO partTime_employee VALUES(?,?,?,?,?,?)";
			st = con.prepareStatement(insertQuery);
			st.setInt(1, empCode);
			st.setString(2, userName);
			st.setString(3, password);
			st.setString(4, name);
			st.setInt(5, salary);
			st.setString(6, empType);
			st.executeUpdate();

		}else if(empType.equals("contract")) {
			String insertQuery = "INSERT INTO contract_employee VALUES(?,?,?,?,?,?)";
			st = con.prepareStatement(insertQuery);
			st.setInt(1, empCode);
			st.setString(2, userName);
			st.setString(3, password);
			st.setString(4, name);
			st.setInt(5, salary);
			st.setString(6, empType);
			st.executeUpdate();
			
		}else {
			
		}
		
		con.close();
		return "user created successfully";
	}
}
