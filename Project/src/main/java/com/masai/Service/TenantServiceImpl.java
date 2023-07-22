package com.masai.Service;

import java.util.List;

import com.masai.Dao.TenantDao;
import com.masai.Dao.TenantDaoImpl;
import com.masai.Entity.Offer;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;

public class TenantServiceImpl implements TenantService{

	static TenantDao dao=new TenantDaoImpl();
	
	@Override
	public void register(Tenant renter) throws SomethingWentWrongEx {
		dao.register(renter);
	}

	@Override
	public void login(String uname, String pwd) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.login(uname, pwd);
	}

	@Override
	public List<Property> getListProperty() throws SomethingWentWrongEx, NoRecordFoundEx {
		return dao.getListProperty();
	}

	

	@Override
	public List<Offer> getListOffer() throws SomethingWentWrongEx, NoRecordFoundEx {
		return dao.getListOffer();
	}

	@Override
	public Offer getOfferStatus(int id) throws SomethingWentWrongEx, NoRecordFoundEx {
		return dao.getOfferStatus(id);
	}

	@Override
	public void giveOffer(int propId, double amount) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.giveOffer(propId, amount);
	}

}
