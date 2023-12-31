package com.masai.Service;

import java.util.List;

import com.masai.Entity.Offer;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;

public interface TenantService {

	void register(Tenant renter)throws SomethingWentWrongEx;
	void login(String uname,String pwd)throws SomethingWentWrongEx,NoRecordFoundEx;
	void getListProperty()throws SomethingWentWrongEx,NoRecordFoundEx;
	void giveOffer(int propId,double amount)throws SomethingWentWrongEx,NoRecordFoundEx;
	void getListOffer()throws SomethingWentWrongEx,NoRecordFoundEx;
	Offer getOfferStatus(int id)throws SomethingWentWrongEx,NoRecordFoundEx;
	public void showmyAgreements() throws SomethingWentWrongEx, NoRecordFoundEx;
}
