package com.masai.Dao;

import java.util.List;

import com.masai.Entity.Offer;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;

public interface TenantDao {

	void register(Tenant renter)throws SomethingWentWrongEx;
	void login(String uname,String pwd)throws SomethingWentWrongEx,NoRecordFoundEx;
	List<Property> getListProperty()throws SomethingWentWrongEx,NoRecordFoundEx;
	void giveOffer(Offer offer)throws SomethingWentWrongEx,NoRecordFoundEx;
	List<Offer> getListOffer()throws SomethingWentWrongEx,NoRecordFoundEx;
	Offer getOfferStatus(int id)throws SomethingWentWrongEx,NoRecordFoundEx;
}
