package com.masai.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tenant {
	@Id
	private String userName;
	private String password;
	private String fullName;
	private String contactInfo;
	
	@OneToMany(mappedBy="propertyId",cascade=CascadeType.ALL)
	private Set<Property> properties;
	
	@OneToMany(mappedBy="offerId",cascade=CascadeType.ALL)
	private Set<Offer> offers;
	
	public Tenant() {
		super();
	}
	public Tenant(String userName, String password, String fullName, String contactInfo) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.contactInfo = contactInfo;
		this.properties=new HashSet<>();
		this.offers=new HashSet<>();	
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
	
	
	public Set<Property> getProperties() {
		return properties;
	}
	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}
	
	
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	
	
}
