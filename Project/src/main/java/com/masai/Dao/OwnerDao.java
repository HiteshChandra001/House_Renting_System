package com.masai.Dao;

import java.util.List;

import com.masai.Entity.Owner;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;

public interface OwnerDao {
	
	void register(Owner landlord)throws SomethingWentWrongEx;
	void login(String uname,String pwd)throws SomethingWentWrongEx,NoRecordFoundEx;
	void addProperty(String location,double amount,int badroom)throws SomethingWentWrongEx,NoRecordFoundEx;
	void updateProperty(int proId,String location,double amount)throws SomethingWentWrongEx,NoRecordFoundEx;
	List<Tenant> getListRenter()throws SomethingWentWrongEx,NoRecordFoundEx;
	void acceptOffer(int offerid)throws SomethingWentWrongEx,NoRecordFoundEx;

}
