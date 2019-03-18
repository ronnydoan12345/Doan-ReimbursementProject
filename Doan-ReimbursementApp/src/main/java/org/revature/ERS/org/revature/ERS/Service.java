package org.revature.ERS.org.revature.ERS;

import java.io.IOException;
import java.sql.SQLException;

public class Service {

public void UpdateService (Employee employee ) throws IOException, SQLException {
		
		EmployeeDao employeeDao = new EmployeeDao();
		
		employeeDao.updateEmployeePersonalInfoById(employee);		
		

	}
//public List<Reimbursement> getAllReimbursements() throws IOException {
//	List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
//	ReimbursementDao reimbursementDao = new ReimbursementDao();
//	reimbList = reimbursementDao.getAllReimbursements();
//	return reimbList;
//}

public void submitReimbursement(Reimbursement reimbursement) throws IOException {
	System.out.println("service");
	ReimbursementDao reimbursementDao = new ReimbursementDao();
	reimbursementDao.submitReimbursement(reimbursement);
	
	
}
//public List<Reimbursement> viewAllReimbursementById(Reimbursement reimbursement) throws IOException {
//	List<Reimbursement> reimbListById = new ArrayList<Reimbursement>();
//	ReimbursementDao reimbursementDao = new ReimbursementDao();
//	reimbListById = reimbursementDao.viewAllReimbursementById(reimbursement);
//	return reimbListById;
//	
//	
//}
	
	
}
