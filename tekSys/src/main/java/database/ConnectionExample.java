package database;

import java.sql.*;

public class ConnectionExample {
	public static void main(String[] args) {
		String dburl = "jdbc:mysql://localhost:3306/classicmodels";
		String user = "root";
		String password = "password";
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
		    connection = DriverManager.getConnection(dburl, user, password);
		    System.out.println("Connection is read only " + connection.isReadOnly());
		    
		    String SelectSQL = "Select * FROM employees";
		    stmt = connection.createStatement();
		    result = stmt.executeQuery(SelectSQL);
		    
		    while(result.next()) {
		    	Integer one = result.getInt(1);
		    	
		    	String name = result.getString("firstName");
		    	String email = result.getString("email");
		    	System.out.println(one + " | " + name + " | " + email);
		    	
		    }
		    
		    System.out.println(result.toString());
		    
		}
		catch(SQLException e) {
		    e.printStackTrace();
		}

	}

}
