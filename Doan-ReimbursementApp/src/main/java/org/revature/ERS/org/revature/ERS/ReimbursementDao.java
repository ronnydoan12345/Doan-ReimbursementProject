package org.revature.ERS.org.revature.ERS;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReimbursementDao {

	public void submitReimbursement(Reimbursement reimbursement) throws IOException {

		PreparedStatement ps = null;
		String status = reimbursement.getStatus();
		String image = reimbursement.getImage();
		Integer amount = reimbursement.getAmount();
		Integer employeeId = reimbursement.getEmployeeId();
		String employeeName = reimbursement.getEmployeeName();
		String email = reimbursement.getEmail();
		String password = reimbursement.getPassword();
		Integer ManagerId = 0;
		String managerEmail = "N/A";
		String managerPassword = "N/A";
		try {
			DBUtilities dbUtilities = new DBUtilities();

			String sqlStatement = "INSERT INTO Reimbursements (status,image, amount, employeeId, employeeName, email, password, ManagerId, managerEmail, managerPassword) VALUES(?,?,?,?,?,?,?,?,?,?)";
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			ps.setString(1, status);
			ps.setString(2, image);
			ps.setInt(3, amount);
			ps.setInt(4, employeeId);
			ps.setString(5, employeeName);
			ps.setString(6, email);
			ps.setString(7, password);
			ps.setInt(8, ManagerId);
			ps.setString(9, managerEmail);
			ps.setString(10, managerPassword);

			ResultSet resultSet = ps.executeQuery();
			dbUtilities.DisconnectFromDB();

		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
	}

	public ArrayList<Reimbursement> viewReimbursementById(String email) {
		PreparedStatement ps = null;
		Reimbursement reimbursement = null;
		ArrayList<Reimbursement> reimbursementRequest = new ArrayList<Reimbursement>();
		try {
			DBUtilities dbutilities = new DBUtilities();
			String sql = "SELECT * FROM Reimbursements INNER JOIN Employee ON Reimbursements.EmployeeId = Employee.id WHERE Employee.email = ?";
			ps = dbutilities.connection.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt("id");
				String status = rs.getString("status");
				String image = rs.getString("image");
				Integer amount = rs.getInt("amount");
				Integer employeeId = rs.getInt("employeeId");
				String employeeName = rs.getString("employeeName");
				String employeeEmail = rs.getString("email");
				String password = rs.getString("password");
				Integer ManagerId = rs.getInt("ManagerId");
				String managerEmail = rs.getString("managerEmail");
				String managerPassword = rs.getString("managerPassword");

				reimbursement = new Reimbursement(id, status, amount, employeeId, employeeName, employeeEmail, password,
						ManagerId, managerEmail, managerPassword);
				reimbursement.setImage(image);

				reimbursementRequest.add(reimbursement);
			}
			dbutilities.DisconnectFromDB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reimbursementRequest;
	}

	public ArrayList<Reimbursement> viewReimbursementAll() {
		PreparedStatement ps = null;
		Reimbursement reimbursement = null;
		ArrayList<Reimbursement> reimbursementRequest = new ArrayList<Reimbursement>();
		try {
			DBUtilities dbutilities = new DBUtilities();
			String sql = "SELECT * FROM Reimbursements";
			ps = dbutilities.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt("id");
				String status = rs.getString("status");
				String image = rs.getString("image");
				Integer amount = rs.getInt("amount");
				Integer employeeId = rs.getInt("employeeId");
				String employeeName = rs.getString("employeeName");
				String employeeEmail = rs.getString("email");
				String password = rs.getString("password");
				Integer ManagerId = rs.getInt("ManagerId");
				String managerEmail = rs.getString("managerEmail");
				String managerPassword = rs.getString("managerPassword");

				reimbursement = new Reimbursement(id, status, amount, employeeId, employeeName, employeeEmail, password,
						ManagerId, managerEmail, managerPassword);
				reimbursement.setImage(image);

				reimbursementRequest.add(reimbursement);
			}
			dbutilities.DisconnectFromDB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reimbursementRequest;

	}

	public void changeReimbursementStatus(Reimbursement reimbursement) throws IOException {

		PreparedStatement ps = null;
		String status = reimbursement.getStatus();
		Integer ManagerId = reimbursement.getManagerId();
		String managerEmail = reimbursement.getManagerEmail();
		String managerPassword = reimbursement.getManagerPassword();
		try {
			DBUtilities dbUtilities = new DBUtilities();

			String sqlStatement = "UPDATE Reimbursements SET status = ?, ManagerId=?, managerEmail=?, managerPassword=? WHERE id=?";
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			ps.setString(1, status);
			ps.setInt(2, ManagerId);
			ps.setString(3, managerEmail);
			ps.setString(4, managerPassword);
			ps.setInt(5, reimbursement.getId());

			ResultSet resultSet = ps.executeQuery();

			dbUtilities.DisconnectFromDB();

		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}

	}
}
