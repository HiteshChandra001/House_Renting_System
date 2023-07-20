package com.masai.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Property {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int propertyId;
	private String location;
	private double amount;
	private int bedrooms;
	private String availability;
	
	@ManyToOne
	@JoinColumn(name="username")
	private Owner owner;
	
	public Property() {
		super();
	}
	public Property(String location, double amount, int bedrooms,Owner owner) {
		super();
		this.location = location;
		this.amount = amount;
		this.bedrooms = bedrooms;
		this.availability="available";
		this.owner=owner;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	public String isavailability() {
		return availability;
	}
	public void setavailability(String availability) {
		this.availability = availability;
	}
	
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	
}
