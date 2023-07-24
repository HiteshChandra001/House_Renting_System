package com.masai.Service;

import java.util.List;

import com.masai.Entity.Owner;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;

public interface OwnerService {

	void register(Owner landlord)throws SomethingWentWrongEx;
	void login(String uname,String pwd)throws SomethingWentWrongEx,NoRecordFoundEx;
	void addProperty(String location,double amount,int rooms)throws SomethingWentWrongEx,NoRecordFoundEx;
	void updateProperty(int proId,String location,double amount,int room)throws SomethingWentWrongEx,NoRecordFoundEx;
	void getListProperty()throws SomethingWentWrongEx,NoRecordFoundEx;
	
	void showOfferList(int propId)throws SomethingWentWrongEx,NoRecordFoundEx;
	void showmyAgreements()throws SomethingWentWrongEx,NoRecordFoundEx;

	void acceptOffer(int offerid)throws SomethingWentWrongEx,NoRecordFoundEx;

}
