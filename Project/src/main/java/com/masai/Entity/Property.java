package com.masai.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
=======
>>>>>>> de74c1fde4168dd12738087e8e355d2e94290cfa
import jakarta.persistence.OneToOne;

@Entity
public class Property {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int propertyId;
	private String location;
	private double amount;
	private int bedrooms;
	private String availability;
	
	@OneToOne	
	@JoinColumn(name="tenant")
	private Tenant tenant;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private Owner owner;
	
	
<<<<<<< HEAD
	@OneToOne
	@JoinColumn(name="tenant")
	private Tenant tenant;
	
	
	@OneToMany(mappedBy="offerId",cascade=CascadeType.ALL)
	private Set<Offer> offers;
	
	
=======
>>>>>>> de74c1fde4168dd12738087e8e355d2e94290cfa
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
		this.tenant=null;
<<<<<<< HEAD
		this.offers=new HashSet<>();
=======
>>>>>>> de74c1fde4168dd12738087e8e355d2e94290cfa
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
	
<<<<<<< HEAD
=======
	
	
>>>>>>> de74c1fde4168dd12738087e8e355d2e94290cfa
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	@Override
	public String toString() {
		return "Property [propertyId=" + propertyId + ", location=" + location + ", amount=" + amount + ", bedrooms="
				+ bedrooms + ", availability=" + availability + "]";
	}
	
	
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	@Override
	public String toString() {
		return "Property [propertyId=" + propertyId + ", location=" + location + ", amount=" + amount + ", bedrooms="
				+ bedrooms + ", availability=" + availability + "]";
	}
	
	
	
}
