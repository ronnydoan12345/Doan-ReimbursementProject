package org.revature.ERS.org.revature.ERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/ViewReimbursementById")

public class ViewReimbursementById extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BufferedReader br = req.getReader();
		JSONObject json = new JSONObject(br.readLine());
		String email = json.getString("email");
        ReimbursementDao reimbursementDao = new ReimbursementDao();
        
        ArrayList<Reimbursement> reimbursementRequests = reimbursementDao.viewReimbursementById(email);
        
        PrintWriter out = res.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(reimbursementRequests);
        out.println(jsonString);
        out.close();
     

	}
	
}
