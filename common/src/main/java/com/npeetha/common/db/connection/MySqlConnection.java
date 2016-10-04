package com.npeetha.common.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.npeetha.common.constants.MySQLConnectionConstants;

public class MySqlConnection {

	public static MySqlConnection myConnection = new MySqlConnection();
	Connection con = null;
	private MySqlConnection() {

	}

	public static MySqlConnection getInstance() {
		return myConnection;
	}

	public Connection getConnection() {
		
		try {
			if (con == null || con.isClosed()) {
				Class.forName(MySQLConnectionConstants.DRIVER_NAME);
				con = DriverManager.getConnection(MySQLConnectionConstants.CONNECTION_STRING,
						MySQLConnectionConstants.USERNAME, MySQLConnectionConstants.PASSWORD);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void openConnection(){
//		if(con!=null && con.isClosed()){
//			con.o
//		}
//	}

}
