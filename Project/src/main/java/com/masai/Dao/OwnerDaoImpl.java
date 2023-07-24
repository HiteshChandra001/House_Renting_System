package com.masai.Dao;

import java.util.List;
import java.util.Set;

import com.masai.Entity.Agreement;
import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Offer;
import com.masai.Entity.Owner;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;
import com.masai.Utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
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
			
			if(prop.getAvailability().equals("Sold")) {
				throw new SomethingWentWrongEx("Property already Sold");
			}
			
			et=em.getTransaction();
			et.begin(); 
			prop.setLocation(location);
			prop.setAmount(amount);
			prop.setBedrooms(room);
			et.commit();
		}catch(IllegalArgumentException |IllegalStateException|PersistenceException e) {
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
				System.out.println("Propery_id:"+p.getPropertyId()+"		"+"status:"+p.getAvailability()+"		"+"Rent_Price:"+p.getAmount()	);
				System.out.println("location:"+p.getLocation()+"		"+"no_of_room:"+p.getBedrooms());
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
			}
		}catch(IllegalArgumentException e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		em.close();
	}

	@Override
	public void acceptOffer(int offerid) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		EntityTransaction et=null;
		try {
			em=EMUtils.getManager();
			Offer offer = em.find(Offer.class, offerid);
			if(offer==null) {
				throw new NoRecordFoundEx("no record found for given id");
			}
			if(!offer.getProperty().getOwner().getUsername().equals(LoggedInUserId.user)) {
				throw new NoRecordFoundEx("invalid offerid");
			}
			
			if(offer.getStatus().equals("Accepted")) {
				throw new SomethingWentWrongEx("offer already accepted");
			}else if(offer.getStatus().equals("rejected")) {
				throw new SomethingWentWrongEx("offer already rejected");
			}
			
			et=em.getTransaction();
			
			
			Property prop = offer.getProperty();
			if(prop.getAvailability().equals("Sold")) {
				throw new SomethingWentWrongEx("Property already Sold");
			}
			
			Query query = em.createQuery("FROM Offer o WHERE o.offerId !=:oid AND o.property.propertyId=:pid");
			query.setParameter("oid",offerid );
			query.setParameter("pid", prop.getPropertyId());
			
			List<Offer>result = query.getResultList();
			Owner owner = prop.getOwner();
			Tenant tenant = offer.getTenant();
			et.begin();
		
			result.forEach(p->p.setStatus("rejected"));
			
			offer.setStatus("Accepted");
			prop.setAvailability("Sold");
			prop.setTenant(tenant);
			tenant.getProperties().add(prop);
			Agreement agm=new Agreement(prop,tenant,offer.getOfferAmount());
			em.persist(agm);
			et.commit();
		}catch(IllegalArgumentException |IllegalStateException|PersistenceException e) {
			if(et!=null) {
				et.rollback();
			}
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		
		em.close();
	}

	@Override
	public void showOfferList(int propId) throws SomethingWentWrongEx, NoRecordFoundEx {
		List<Offer> list=null;
		EntityManager em=null;
		try {
			em=EMUtils.getManager();
			Property p = em.find(Property.class, propId);
			if(p==null) {
				throw new NoRecordFoundEx("No Record Found for given id");
			}
			
			if(!p.getOwner().getUsername().equals(LoggedInUserId.user)) {
				throw new SomethingWentWrongEx("invalid property id");
			}
			Query query = em.createQuery("FROM Offer p WHERE p.property.propertyId=:id");
			query.setParameter("id", propId);
			
			list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("No Record Found");
			}
			System.out.println("Propery_id:"+p.getPropertyId()+"		"+"status:"+p.getAvailability()+"		"+"Rent_Price:"+p.getAmount()	);
			System.out.println("location:"+p.getLocation()+"		"+"no_of_room:"+p.getBedrooms());
			System.out.println("--------------------------------------------------------------------------------");
		
			for(Offer o:list) {
				System.out.println("offerId:"+o.getOfferId()+"		"+"amount:"+o.getOfferAmount()+"		"+"status:"+o.getStatus());
				System.out.println("Buyer:"+o.getTenant().getFullName()+"		"+"contact no.: "+o.getTenant().getContactInfo());	
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
			}
			
		}catch(IllegalArgumentException |IllegalStateException|PersistenceException e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		em.close();
		
	}

	@Override
	public void showmyAgreements() throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Agreement p WHERE p.property.owner.username=:un");
			query.setParameter("un", LoggedInUserId.user);
			List<Agreement>list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("No record Found");
			}
			
			for(Agreement a:list) {
				System.out.println(a.getProperty());
				System.out.println("agreementid: "+a.getAgreementId()+"		"+"amount: "+a.getAmount()+"		"+"Tenant: "+a.getTenant().getUserName());
			}
			
		}catch(IllegalArgumentException |IllegalStateException|PersistenceException e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
			
		em.close();
			
	}

	public void showTenantList() throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		try {
			em=EMUtils.getManager();
			Query query = em.createQuery("FROM Property p WHERE p.owner.username=:un");
			query.setParameter("un", LoggedInUserId.user);
			List<Property>list = query.getResultList();
			if(list.size()==0) {
				throw new NoRecordFoundEx("No record Found");
			}
			
			List<Tenant> filter = list.stream().filter(p->p.getTenant()!=null).map(p->p.getTenant()).toList();
			if(filter.size()==0) {
				throw new NoRecordFoundEx("No record Found");
			}
			for(Tenant t:filter) {
				System.out.println("username: "+t.getUserName()+"	"+"name: "+t.getFullName()+"		"+"contact: "+t.getContactInfo());
				System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
			}
			
		}catch(IllegalArgumentException |IllegalStateException|PersistenceException e) {
			throw new SomethingWentWrongEx("Something Went Wrong");
		}
		em.close();
	}

}
