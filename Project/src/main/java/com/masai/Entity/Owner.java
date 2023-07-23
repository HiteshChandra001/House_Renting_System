package com.masai.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Owner {
	@Id
	private String username;
	private String password;
	private String fullName;
	private String contantInfo;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL,fetch =FetchType.LAZY)
	private Set<Property> properties;
	
	public Owner() {
		super();
	}

	public Owner( String username, String password, String fullName, String contantInfo) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.contantInfo = contantInfo;
		this.properties=new HashSet<>();
	}


	public String getUsername() {
		return username;
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

	public String getContantInfo() {
		return contantInfo;
	}

	public void setContantInfo(String contantInfo) {
		this.contantInfo = contantInfo;
	}
	
	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

		
}
