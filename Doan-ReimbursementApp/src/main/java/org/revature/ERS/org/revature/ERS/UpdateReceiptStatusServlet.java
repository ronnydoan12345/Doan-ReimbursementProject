package org.revature.ERS.org.revature.ERS;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/UpdateReceiptStatusServlet")

public class UpdateReceiptStatusServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BufferedReader br = req.getReader();
		JSONObject json = new JSONObject(br.readLine());
        ReimbursementDao reimbursementDao = new ReimbursementDao();
        Reimbursement reimbursement = new Reimbursement(json.getInt("id"), json.getString("status"), json.getInt("managerId"), json.getString("manageremail"), json.getString("managerpassword"));
        reimbursementDao.changeReimbursementStatus(reimbursement);
        
     

	}
}
