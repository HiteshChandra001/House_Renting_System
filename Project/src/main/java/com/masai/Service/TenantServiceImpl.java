package com.masai.Service;

import java.util.List;

import com.masai.Entity.Offer;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;

public class TenantServiceImpl implements TenantService{

	@Override
	public void register(Tenant renter) throws SomethingWentWrongEx {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String uname, String pwd) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Property> getListProperty() throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void giveOffer(Offer offer) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Offer> getListOffer() throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer getOfferStatus(int id) throws SomethingWentWrongEx, NoRecordFoundEx {
		// TODO Auto-generated method stub
		return null;
	}

}
