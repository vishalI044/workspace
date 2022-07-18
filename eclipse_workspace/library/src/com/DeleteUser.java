package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {
	public static String deleteUser(int empCode, String empType, Connection con) throws ClassNotFoundException, SQLException {
		//Connection con = DBConnection.initialise();
		PreparedStatement st = null;
		if(empType.equals("permanent")) {
			String delQuery = "DELETE FROM permanent_employee WHERE empCode =?";
			st = con.prepareStatement(delQuery);
			st.setInt(1, empCode);
			st.executeUpdate();
			
			return "user deleted";
		}else if(empType.equals("partTime")) {
			String delQuery = "DELETE FROM partTime_employee WHERE empCode =?";
			st = con.prepareStatement(delQuery);
			st.setInt(1, empCode);
			st.executeUpdate();

			return "user deleted";
		}else if(empType.equals("contract")) {
			String delQuery = "DELETE FROM contract_employee WHERE empCode =?";
			st = con.prepareStatement(delQuery);
			st.setInt(1, empCode);
			st.executeUpdate();
			
			return "user deleted";
		}
		return "user not deleted";
	}
}
