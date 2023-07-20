package com.masai.Dao;

import java.util.List;

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
		if(owner!=null) {
			throw new SomethingWentWrongEx("Username Already Present");
		}
		LoggedInUserId.user=uname;
		
		}catch(IllegalArgumentException  e) {
			throw new SomethingWentWrongEx("Something went wrong");
		}
		
	}

	@Override
	public void addProperty(Property property) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProperty(int proId, String location, double amount) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tenant> getListRenter() throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void acceptOffer(int offerid) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		
	}

}
