package org.revature.ERS.org.revature.ERS;

public class Manager {
	private Integer id;
	private String fullname;
	private String email;
	private String password;

	public Manager() {
	}

	public Manager(String fullname, String email, String password) {
		this.fullname = fullname;
		this.email = email;
		this.password = password;

	}

	public Manager(Integer id, String fullname, String email, String password) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
