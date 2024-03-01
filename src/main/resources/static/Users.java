package com.project.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection= "users")
public class Users {
	
	
	
	@Id
	private String email;
	private String usrName;
	private String fname;
	private String lname;
	private String password;
	private long mNumber;
	private String blocked;
	
	
	
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getmNumber() {
		return mNumber;
	}
	public void setmNumber(long mNumber) {
		this.mNumber = mNumber;
	}
	public Users(String email, String usrName, String fname, String lname, String password, long mNumber, String blocked) {
		super();
		this.email = email;
		this.usrName = usrName;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.mNumber = mNumber;
		this.blocked=blocked;
	}
	
	

}
