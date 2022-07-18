package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class ListUser {
	public static JSONObject listUser(String empType, Connection con) throws SQLException, ClassNotFoundException {
		
		JSONObject jo = new JSONObject();
		//Connection con = DBConnection.initialise();
		PreparedStatement st = null;
		ResultSet rs = null;
		if(empType.equals("permanent")) {
			String insertQuery = "SELECT * FROM employee_database.permanent_employee;"; 
			st = con.prepareStatement(insertQuery);
			//st.executeQuery();
			rs = st.executeQuery();
			
			while(rs.next()) {
				jo.put("empCode", rs.getInt(1));
				jo.put("empType", rs.getString(2));
				jo.put("userName", rs.getString(3));
				jo.put("password", rs.getString(4));
				jo.put("salary", rs.getInt(5));
				jo.put("name", rs.getString(6));
			}
			return (jo);
		}else if(empType.equals("partTime")) {
			String insertQuery = "SELECT * FROM employee_database.partTime_employee;";
			st = con.prepareStatement(insertQuery);
			//st.executeQuery();
			rs = st.executeQuery();
			
			while(rs.next()) {
				jo.put("empCode", rs.getInt(1));
				jo.put("empType", rs.getString(2));
				jo.put("userName", rs.getString(3));
				jo.put("password", rs.getString(4));
				jo.put("salary", rs.getInt(5));
				jo.put("name", rs.getString(6));
			}
			return (jo);
		}else if(empType.equals("contract")) {
			String insertQuery = "SELECT * FROM employee_database.contract_employee;";
			st = con.prepareStatement(insertQuery);
			//st.executeQuery();
			rs = st.executeQuery();
			
			while(rs.next()) {
				jo.put("empCode", rs.getInt(1));
				jo.put("empType", rs.getString(2));
				jo.put("userName", rs.getString(3));
				jo.put("password", rs.getString(4));
				jo.put("salary", rs.getInt(5));
				jo.put("name", rs.getString(6));
			}
			return (jo);
		}
		return jo;
	}
}
