package com.project.Model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Admin-cred")
public class AdminCred {
	
	@Field("usrName")
	private String usrName;
	@Field("pwd")
	private String pwd;
	
	@Field("access")
	private String access;
	
	public AdminCred() {
        // Default constructor
    }
	
	
	public String getAccess() {
		return access;
	}


	public void setAccess(String access) {
		this.access = access;
	}


	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public AdminCred(String usrName, String pwd) {
		super();
		this.usrName = usrName;
		this.pwd = pwd;
	}
	

}




