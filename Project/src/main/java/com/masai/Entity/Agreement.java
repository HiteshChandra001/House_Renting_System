package com.masai.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity	
public class Agreement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int agreementId;
	
	@OneToOne
	private Property property;
	
	@OneToOne
	private Tenant tenant;
	
	@OneToOne
	private Owner owner;
	
	private double amount;
	
	public Agreement() {
		super();
	}

	public Agreement( Property property, Tenant tenant, double amount) {
		super();
		this.property = property;
		this.tenant = tenant;
		this.amount = amount;
	}

	public int getAgreementId() {
		return agreementId;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
}
