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
	public void addProperty(String location,double amount,int rooms) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.addProperty( location, amount, rooms);
	}

	@Override
	public void updateProperty(int proId, String location, double amount,int room) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.updateProperty(proId, location, amount, room);
	}

	@Override
	public void getListProperty() throws SomethingWentWrongEx, NoRecordFoundEx {
		 dao.getListProperty();

	}

	@Override
	public void acceptOffer(int offerid) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.acceptOffer(offerid);
	}

	@Override
	public void showOfferList(int propId) throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.showOfferList(propId);
	}

	@Override
	public void showmyAgreements() throws SomethingWentWrongEx, NoRecordFoundEx {
		dao.showmyAgreements();
	}

}
