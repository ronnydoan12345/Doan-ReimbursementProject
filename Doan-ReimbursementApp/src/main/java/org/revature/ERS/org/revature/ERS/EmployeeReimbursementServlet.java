package org.revature.ERS.org.revature.ERS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/EmployeeReimbursementServlet")

public class EmployeeReimbursementServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Service service = new Service();		
		System.out.println("servlet");


		
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement reimbursement = mapper.readValue(req.getInputStream(), Reimbursement.class);		
		service.submitReimbursement(reimbursement);
     

	}

}
