package com.masai.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Tenant {
	@Id
	private String userName;
	private String password;
	private String fullName;
	private String contactInfo;
	public Tenant() {
		super();
	}
	public Tenant(String userName, String password, String fullName, String contactInfo) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.contactInfo = contactInfo;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	
}
