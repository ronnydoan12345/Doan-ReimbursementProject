package org.revature.ERS.org.revature.ERS;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao {

	public boolean checkTheEmailAndPassword (Employee employee)throws SQLException, IOException {
		PreparedStatement ps = null;
		boolean isEmail = false;
		ArrayList<String> employeeEmail = new ArrayList<String>();
		
		try {
			DBUtilities dbUtilities = new DBUtilities();
			
			String sqlStatement = "SELECT email, password FROM Employee WHERE email =? AND password=?";
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			ps.setString(1, employee.getEmail());
			ps.setString(2, employee.getPassword());
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				
				employeeEmail.add(resultSet.getString("email"));
				employeeEmail.add(resultSet.getString("password"));
			}
			
			if (employeeEmail.contains(employee.getEmail()) && employeeEmail.contains(employee.getPassword())) {
				isEmail = true;
			}
			
			
			dbUtilities.DisconnectFromDB();
			
			
		}catch(SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
		
		return isEmail;
		
	}
	
	public void updateEmployeePersonalInfoById (Employee employee) throws SQLException, IOException {
		PreparedStatement ps = null;
		Employee emp = null;
		try {
			DBUtilities dbUtilities = new DBUtilities();
			String sqlStatement = "UPDATE Employee SET fullname = ?, email = ?, password = ? WHERE id = ?";
			
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			
			ps.setString(1, employee.getFullname());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPassword());
			ps.setInt(4, employee.getId());
			ps.executeUpdate();
			
			dbUtilities.DisconnectFromDB();
		}catch(SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
	}
	
	
	
	public Employee getEmployeeByEmailAndPassword (Employee employee) throws SQLException, IOException {
		PreparedStatement ps = null;
		Employee emp = null;
		try {
			DBUtilities dbUtilities = new DBUtilities();
			String sqlStatement = "SELECT * from Employee WHERE email=? AND password=?";
			
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			ps.setString(1,employee.getEmail());
			ps.setString(2,employee.getPassword());
			ResultSet resultSet = ps.executeQuery();
			
			
			while(resultSet.next()) {
				emp = new Employee();
				emp.setId(resultSet.getInt("id"));
				emp.setPosition(resultSet.getString("position"));
				emp.setFullname(resultSet.getString("fullname"));
				emp.setEmail(resultSet.getString("email"));
				emp.setPassword(resultSet.getString("password"));
				
			}
			
			dbUtilities.DisconnectFromDB();
		}catch(SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
		return emp;
	}
	
	

	public ArrayList<Employee> getAllEmployees() throws SQLException, IOException, ClassNotFoundException {

		PreparedStatement ps = null;
		Employee emp = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {
			DBUtilities dbUtilities = new DBUtilities();

			String sqlStatement = "SELECT * FROM Employee";
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				emp = new Employee();
				emp.setId(resultSet.getInt("id"));
				emp.setFullname(resultSet.getString("fullname"));
				emp.setEmail(resultSet.getString("email"));
				emp.setPassword(resultSet.getString("password"));

				employees.add(emp);

			}
			dbUtilities.DisconnectFromDB();

		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
		return employees;

	}

	public void RegisterEmployee(Employee employee) throws IOException  {

		CallableStatement cs = null;

		try {
			DBUtilities dbUtilities = new DBUtilities();
			String sql = "{CALL RegisterEmployee(?, ?, ?, ?)}";
			cs = dbUtilities.connection.prepareCall(sql);
			cs.setString(1, employee.getPosition());
			cs.setString(2, employee.getFullname());
			cs.setString(3, employee.getEmail());
			cs.setString(4, employee.getPassword());

			cs.execute();

			cs.close();
			dbUtilities.connection.close();

		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}

	}

}
