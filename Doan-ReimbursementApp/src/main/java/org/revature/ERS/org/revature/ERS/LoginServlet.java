package org.revature.ERS.org.revature.ERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
            
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		PrintWriter output = res.getWriter();
		BufferedReader br = req.getReader();
		RegisterService registerService = new RegisterService();		
		
		JSONObject json = new JSONObject(br.readLine());
		Employee employee = new Employee(json.getString("email"), json.getString("password"));
		try {
				Employee employeeData = registerService.getEmployeeByEmailAndPassword(employee);
				if(employeeData.getPosition().equals("Employee")) {
					
					JSONObject employeeJSON = new JSONObject();
					employeeJSON.put("id", employeeData.getId());
					employeeJSON.put("position", employeeData.getPosition());
					employeeJSON.put("fullname", employeeData.getFullname());
					employeeJSON.put("email", employeeData.getEmail());
					employeeJSON.put("password", employeeData.getPassword());
					output.print(employeeJSON);
					
					
				}else if (employeeData.getPosition().equals("Manager")){
					
					JSONObject managerJSON = new JSONObject();
					managerJSON.put("id", employeeData.getId());
					managerJSON.put("position", employeeData.getPosition());
					managerJSON.put("fullname", employeeData.getFullname());
					managerJSON.put("email", employeeData.getEmail());
					managerJSON.put("password", employeeData.getPassword());
					output.print(managerJSON);
					
				}else {
					System.out.println("There is no account registered under this email and password.");
				}				
				
		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}

        
        
	}
}
