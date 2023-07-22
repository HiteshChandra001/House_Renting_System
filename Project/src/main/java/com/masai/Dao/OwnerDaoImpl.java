package com.masai.Dao;

import java.util.List;

import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Offer;
import com.masai.Entity.Owner;
import com.masai.Entity.Property;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;
import com.masai.Utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class OwnerDaoImpl implements OwnerDao{

	@Override
	public void register(Owner owner) throws SomethingWentWrongEx {
		EntityManager em=null;
		EntityTransaction et=null;
		try{
			em=EMUtils.getManager();
			
			Owner find = em.find(Owner.class, owner.getUsername());
			if(find!=null) {
			throw new SomethingWentWrongEx("Username already exist");	
			}
			et=em.getTransaction();
			
			et.begin();
			em.persist(owner);
			et.commit();
		}catch(IllegalStateException | IllegalArgumentException  e) {
			if(et!=null) {
				et.rollback();
			}
			e.printStackTrace();
		}
		em.close();
	}

	@Override
	public void login(String uname, String pwd) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		
		try {
		em=EMUtils.getManager();
		Owner owner = em.find(Owner.class,uname);
		if(owner==null) {
			throw new NoRecordFoundEx("No Record Found for Username");
		}else if(owner.getPassword().equals(pwd)){
			LoggedInUserId.user=uname;
		}else {
			throw new SomethingWentWrongEx("Wrong username or password");
		}
		
		}catch(IllegalArgumentException  e) {
			throw new SomethingWentWrongEx("Something went wrong");
		}
		em.close();
	}

	@Override
	public void addProperty(String location,double amount,int rooms) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		EntityTransaction et=null;
		try{
			em=EMUtils.getManager();
			
			Owner owner = em.find(Owner.class, LoggedInUserId.user);
			Property prop=new Property(location,amount,rooms,owner);
			owner.getProperties().add(prop);
			
			et=em.getTransaction();
			et.begin();
			em.persist(prop);
			et.commit();
		}catch(IllegalStateException | IllegalArgumentException  e) {
			if(et!=null) {
				et.rollback();
			}
			e.printStackTrace();
		}
		em.close();
	}

	@Override
	public void updateProperty(int proId, String location, double amount,int room) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		EntityTransaction et=null;
		try{
			em=EMUtils.getManager();
			
			Property prop = em.find(Property.class, proId);
			if(prop==null) {
				throw new NoRecordFoundEx("No Record found for propertyId");
			}
			
			if(!prop.getOwner().getUsername().equals(LoggedInUserId.user)) {
				throw new NoRecordFoundEx("No Record found for propertyId");
			}
			
			et=em.getTransaction();
			et.begin(); 
			prop.setLocation(location);
			prop.setAmount(amount);
			prop.setBedrooms(room);
			et.commit();
		}catch(Exception  e) {
			if(et!=null) {
				et.rollback();
			}
			throw new SomethingWentWrongEx("some thing went wrong");
		}
		em.close();
	}

	@Override
	public void getListProperty() throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Property p WHERE p.owner.username=:un");
			query.setParameter("un", LoggedInUserId.user);
			List<Property>list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("No record Found");
			}
			for(Property p:list) {
				
				System.out.println("id:"+p.getPropertyId()+"		"+"status:"+p.getAvailability()+"		"+"Rent_Price:"+p.getAmount()	);
				System.out.println("location:"+p.getLocation()+"		"+"no_of_room:"+p.getBedrooms()+"		"+"offers : "+p.getOffers().size());
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
			}
		}catch(IllegalArgumentException e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		em.close();
	}

	@Override
	public void acceptOffer(int offerid) throws SomethingWentWrongEx, NoRecordFoundEx {
	
	}

	@Override
	public List<Offer> getOfferList(int propId) throws SomethingWentWrongEx, NoRecordFoundEx {
		List<Offer> list=null;
		EntityManager em=null;
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Offer p WHERE p.property.propertyId=:id");
			query.setParameter("id", propId);
			
			list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("No Record Found");
			}
			
		}catch(Exception e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		em.close();
		
		return list;
	}

}
