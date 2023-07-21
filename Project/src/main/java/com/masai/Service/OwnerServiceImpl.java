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
	public void register(Owner landlord) throws SomethingWentWrongEx {
		dao.register(landlord);
	}

	@Override
	public void login(String uname, String pwd) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.login(uname, pwd);
	}

	@Override
	public void addProperty(String location, double amount, int badroom) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.addProperty(location, amount, badroom);
	}

	@Override
	public void updateProperty(int proId, String location, double amount) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.updateProperty(proId, location, amount);
	}

	@Override
	public List<Tenant> getListRenter() throws SomethingWentWrongEx, NoRecordFoundEx {
		return dao.getListRenter();
	}

	@Override
	public void acceptOffer(int offerid) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		
	}

}
