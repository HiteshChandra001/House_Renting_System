package com.masai.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int offerId;
	
	@ManyToOne
	@JoinColumn(name="propertyId",nullable=false)
	private Property property;	
	
	@ManyToOne
	@JoinColumn(name="tenantId",nullable=false)
	private Tenant renter;
	
	@Column(nullable=false)
	private double offerAmount;

	private String status;
	
	public Offer() {
		super();
	}

	public Offer(Property property, Tenant renter, double offerAmount) {
		super();
		this.property = property;
		this.renter = renter;
		this.offerAmount = offerAmount;
		this.status="pending";
	}

	public int getOfferId() {
		return offerId;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Tenant getRenter() {
		return renter;
	}

	public void setRenter(Tenant renter) {
		this.renter = renter;
	}

	public double getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(double offerAmount) {
		this.offerAmount = offerAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}