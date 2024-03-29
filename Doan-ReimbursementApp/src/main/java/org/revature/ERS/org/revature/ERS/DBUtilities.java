package org.revature.ERS.org.revature.ERS;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtilities {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;

	public DBUtilities() throws SQLException, IOException {

		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {

        }
		
		
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://myersdb.cgzlzv0lyi3b.us-east-2.rds.amazonaws.com:5432/myersdb", "myersdb",
					"myersdb123");
		} catch (SQLException ex) {
			System.out.println("The following error has occured: " + ex.getMessage());
		}

	}
	
	public void DisconnectFromDB() {

        try {
            resultSet.close();
            statement.close();
            preparedStatement.close();
            connection.close();
        }                                              
        catch (Exception ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }   
    }

}
