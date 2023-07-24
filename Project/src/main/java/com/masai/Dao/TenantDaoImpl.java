package com.masai.Dao;

import java.util.List;

import com.masai.Entity.Agreement;
import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Offer;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;
import com.masai.Utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
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
		
		}catch(IllegalStateException | IllegalArgumentException  e) {
			throw new SomethingWentWrongEx("Something went wrong");
		}
		
		em.close();
	}

	@Override
	public void getListProperty() throws SomethingWentWrongEx, NoRecordFoundEx {
	
		List<Property> list=null;
		EntityManager em=null;
		
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Property p WHERE p.availability =:av");
			query.setParameter("av", "available");
			list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("NO record found");
			}
			for(Property p:list) {
				System.out.println("id:"+p.getPropertyId()+"		"+"status:"+p.getAvailability()+"		"+"Rent_Price:"+p.getAmount()	);
				System.out.println("location:"+p.getLocation()+"		"+"no_of_room:"+p.getBedrooms());
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
			}		
			
		}catch(IllegalStateException | IllegalArgumentException  e) {
			e.printStackTrace();
//			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		em.close();
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
			if(property.getAvailability().equals("Sold")) {
				throw new SomethingWentWrongEx("Property is unavailable");
			}
			
			Offer offer=new Offer(property,tenant,amount);
		
			et=em.getTransaction();
			et.begin();
			tenant.getOffers().add(offer);
			property.getOffers().add(offer);
			em.persist(offer);
			et.commit();			
		}catch(IllegalStateException | IllegalArgumentException  e) {
			if(et!=null) {
			et.rollback();
			}
			throw new SomethingWentWrongEx("SOmething went wrong");
		}
		
		em.close();
	}

	@Override
	public void getListOffer() throws SomethingWentWrongEx, NoRecordFoundEx {
		List<Offer> list=null;	
		EntityManager em=null;
		
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Offer o WHERE o.tenant.userName=:un");
		
			query.setParameter("un", LoggedInUserId.user);
			
			list= query.getResultList();
			
			for(Offer o:list) {
				System.out.println("offerId:"+o.getOfferId()+"		"+"myoffer:"+o.getOfferAmount()+"		"+o.getStatus());
				Property p=o.getProperty();
				System.out.println("id:"+p.getPropertyId()+"		"+"status:"+p.getAvailability()+"		"+"Rent_Price:"+p.getAmount()	);						
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
			}
			if(list.size()==0) {
				throw new NoRecordFoundEx("No Record Found");
			}
			
		}catch(IllegalStateException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		em.close();
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
		}catch(IllegalStateException | IllegalArgumentException  e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		
		return offer;
	}
	
	@Override
	public void showmyAgreements() throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Agreement p WHERE p.property.tenant.userName=:un");
			query.setParameter("un", LoggedInUserId.user);
			List<Agreement>list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("No record Found");
			}
			
			for(Agreement a:list) {
				System.out.println(a.getProperty());
				System.out.println("agreementid: "+a.getAgreementId()+"		"+"amount: "+a.getAmount()+"		"+"Owner: "+a.getProperty().getOwner().getUsername());
			}
			
		}catch(IllegalArgumentException |IllegalStateException|PersistenceException e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
			
		em.close();
			
	}

}
