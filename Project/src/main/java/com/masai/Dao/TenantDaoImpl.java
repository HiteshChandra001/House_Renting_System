package com.masai.Dao;

import java.util.List;

import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Offer;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;
import com.masai.Utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class TenantDaoImpl implements TenantDao{

	@Override
	public void register(Tenant tenant) throws SomethingWentWrongEx {
		EntityManager em=null;
		EntityTransaction et=null;
		try{
			em=EMUtils.getManager();
			
			Tenant find = em.find(Tenant.class, tenant.getUserName());
			if(find!=null) {
			throw new SomethingWentWrongEx("Username already exist");	
			}
			et=em.getTransaction();
			
			et.begin();
			em.persist(tenant);
			et.commit();
		}catch(IllegalStateException | IllegalArgumentException  e) {
			et.rollback();
			e.printStackTrace();
		}
		em.close();
	}

	@Override
	public void login(String uname, String pwd) throws SomethingWentWrongEx, NoRecordFoundEx {
<<<<<<< HEAD
		EntityManager em=null;
		
		try {
		em=EMUtils.getManager();
		Tenant tenant = em.find(Tenant.class,uname);
		if(tenant==null) {
			throw new NoRecordFoundEx("No Record Found for Username");
		}else if(tenant.getPassword().equals(pwd)){
			LoggedInUserId.user=uname;
		}else {
			throw new SomethingWentWrongEx("Wrong username or password");
		}
		
		}catch(IllegalArgumentException  e) {
			throw new SomethingWentWrongEx("Something went wrong");
		}
		
		em.close();
=======
		// TODO Auto-generated method 
>>>>>>> de74c1fde4168dd12738087e8e355d2e94290cfa
	}

	@Override
	public List<Property> getListProperty() throws SomethingWentWrongEx, NoRecordFoundEx {
	
		List<Property> list=null;
		EntityManager em=null;
		
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Property");
			list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("NO record found");
			}
		}catch(IllegalArgumentException e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		em.close();
		return list;
	}

	@Override
	public void giveOffer(int propId,double amount) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		EntityTransaction et=null;
		
		try {
			em=EMUtils.getManager();
			Tenant tenant = em.find(Tenant.class, LoggedInUserId.user);
			Property property = em.find(Property.class, propId);
			if(property==null) {
				throw new NoRecordFoundEx("No Record found");
			}
			if(property.getAvailability().equals("Unavailable")) {
				throw new SomethingWentWrongEx("Property is unavailable");
			}
			
			Offer offer=new Offer(property,tenant,amount);
			tenant.getOffers().add(offer);
			property.getOffers().add(offer);
			
			et=em.getTransaction();
			et.begin();
			em.persist(offer);
			et.commit();			
		}catch(Exception e) {
			et.rollback();
			throw new SomethingWentWrongEx("SOmething went wrong");
		}
		
		em.close();
	}

	@Override
	public List<Offer> getListOffer() throws SomethingWentWrongEx, NoRecordFoundEx {
		List<Offer> list=null;	
		EntityManager em=null;
		
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Offer o WHERE o.tenant.userName=:un");
		
			query.setParameter("un", LoggedInUserId.user);
			
			list= query.getResultList();
			
			if(list.size()==0) {
				throw new NoRecordFoundEx("No Record Found");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Offer getOfferStatus(int id) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		Offer offer=null;
		try {
			em=EMUtils.getManager();
			offer = em.find(Offer.class, id);
			if(!offer.getTenant().getUserName().equals(LoggedInUserId.user)) {
				throw new NoRecordFoundEx("No Record Found");
			}
		}catch(Exception e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		
		return offer;
	}

}
