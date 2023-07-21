package com.masai.Dao;

import java.util.List;
import java.util.Set;import java.util.stream.Collectors;

import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Owner;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
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
				throw new SomethingWentWrongEx("Username Already Present");
			}
			
			et=em.getTransaction();

			et.begin();
			em.persist(owner);
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
		Owner owner = em.find(Owner.class,uname);
		if(owner==null) {
			throw new NoRecordFoundEx("No Record Found for username");
		}
		
		if(owner.getPassword().equals(pwd)) {
			LoggedInUserId.user=owner.getUsername();
		}else {
			throw new SomethingWentWrongEx("Wrong username or password");
		}
		
		}catch(IllegalArgumentException  e) {
			throw new SomethingWentWrongEx("Something went wrong");
		}
		
		em.close();
		
	}

	@Override
	public void addProperty(String location,double amount,int badroom) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		EntityTransaction et=null;
		try{
			em=EMUtils.getManager();
			Owner owner = em.find(Owner.class,LoggedInUserId.user);
			
			Property prop=new Property(location,amount,badroom,owner);
			et=em.getTransaction();

			et.begin();
			em.persist(prop);
			et.commit();
		}catch(IllegalStateException | IllegalArgumentException  e) {
			et.rollback();
			e.printStackTrace();
		}
		em.close();
	}

	@Override
	public void updateProperty(int proId, String location, double amount) throws SomethingWentWrongEx, NoRecordFoundEx {
		EntityManager em=null;
		EntityTransaction et=null;
		try{
			em=EMUtils.getManager();
			
			Query query = em.createQuery("FROM Property p WHERE p.propertyId=:id AND p.owner.username=:un");
			query.setParameter("id", proId);
			query.setParameter("un", LoggedInUserId.user);
			
			List<Property> list= query.getResultList();
			System.out.println(list);
			
			
		}catch(IllegalStateException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		em.close();
	}

	@Override
	public List<Tenant> getListRenter() throws SomethingWentWrongEx, NoRecordFoundEx {
		List<Tenant> list=null;
		EntityManager em=null;
		try{
			em=EMUtils.getManager();
			Owner owner = em.find(Owner.class, LoggedInUserId.user);
			Set<Property> properties = owner.getProperties();
			list = properties.stream().filter(p->p.getTenant()!=null).map(p->p.getTenant()).toList();
			
		}catch(IllegalArgumentException  e) {
			throw new SomethingWentWrongEx("Something went wrong");
		}
		
		return list;
	}

	@Override
	public void acceptOffer(int offerid) throws SomethingWentWrongEx, NoRecordFoundEx {
		
	}

}
