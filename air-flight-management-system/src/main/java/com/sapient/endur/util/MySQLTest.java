package com.sapient.endur.util;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLTest {
	public static void main(String[] args) {
		
	
	try(
			Connection connection= MySQLConnection.getConnection();
			) {
		System.out.println(connection.getTransactionIsolation());
	}catch(SQLException e) {
		e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}


}
}
