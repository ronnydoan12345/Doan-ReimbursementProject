package org.revature.ERS.org.revature.ERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter output = res.getWriter();
		output.write("GET");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		PrintWriter output = res.getWriter();

		BufferedReader br = req.getReader();
		
		JSONObject json = new JSONObject(br.readLine());

		Employee employee = new Employee(json.getString("position"), json.getString("fullname"), json.getString("email"), json.getString("password"));
		
		RegisterService registerService = new RegisterService();
				
		registerService.RegisterEmployee(employee); 

	}

}
