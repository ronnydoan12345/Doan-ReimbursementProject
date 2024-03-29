package org.revature.ERS.org.revature.ERS;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
@WebServlet("/ManagerReimbursementServlet")

public class ManagerReimbursementServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
        ReimbursementDao reimbursementDao = new ReimbursementDao();
        
        ArrayList<Reimbursement> reimbursementRequests = reimbursementDao.viewReimbursementAll();
        
        PrintWriter out = res.getWriter();
        ObjectMapper mapper = new ObjectMapper();


        
        String jsonString = mapper.writeValueAsString(reimbursementRequests);
        out.println(jsonString);
        out.close();
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
	}
}
