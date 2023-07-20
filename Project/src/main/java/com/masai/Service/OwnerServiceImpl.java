package com.masai.Service;

import java.util.List;

import com.masai.Dao.OwnerDao;
import com.masai.Dao.OwnerDaoImpl;
import com.masai.Entity.Owner;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;

public class OwnerServiceImpl implements OwnerService{

	static OwnerDao dao=new OwnerDaoImpl();
	@Override
	public void register(Owner owner) throws SomethingWentWrongEx {
		dao.register(owner);
	}

	@Override
	public void login(String uname, String pwd) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		
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
