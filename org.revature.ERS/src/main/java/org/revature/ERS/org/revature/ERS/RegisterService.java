package org.revature.ERS.org.revature.ERS;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterService {

	
	public void RegisterEmployee (Employee employee ) throws IOException {
		
		EmployeeDao employeeDao = new EmployeeDao();
		
		employeeDao.RegisterEmployee(employee);		
		

	}
	
	public boolean LoginService (Employee employee) throws SQLException, IOException {
		
		EmployeeDao employeeDao = new EmployeeDao();
		
		boolean isEmail = employeeDao.checkTheEmailAndPassword(employee);
		if(isEmail) {
			return true;
		}else {
			return false;
		}
		
	}

	public Employee getEmployeeByEmailAndPassword(Employee employee) throws SQLException, IOException {
		EmployeeDao employeeDao = new EmployeeDao();
		
		Employee employeeData = employeeDao.getEmployeeByEmailAndPassword(employee);
		
		if(employeeData == null) {
			return null;
		}else {
			return employeeData;
		}
		
		
	}
	
	
	
}
