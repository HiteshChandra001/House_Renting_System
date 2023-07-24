package com.masai.Project;

import java.util.List;
import java.util.Scanner;

import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Offer;
import com.masai.Entity.Tenant;
import com.masai.Entity.Property;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;
import com.masai.Service.TenantService;
import com.masai.Service.TenantServiceImpl;

public class TenantUi {

static TenantService serv=new TenantServiceImpl();
	
	static void registration(Scanner sc) {
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		System.out.println("Enter Username");
		String un=sc.next();
		System.out.println("Enter Password");
		String pwd=sc.next();
		sc.nextLine();
		System.out.println("Enter FullName");
		String fn=sc.nextLine();
		System.out.println("Enter Contact ");
		String con=sc.next();
		
		Tenant tenant=new Tenant(un,pwd,fn,con);
		try {
			serv.register(tenant);
		} catch (SomethingWentWrongEx e) {
			e.printStackTrace();
		}
	}
	
	static void login(Scanner sc) {
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		System.out.println("ENter Username");
		String un=sc.next();
		System.out.println("Enter Password");
		String pwd=sc.next();
		
		try {
			serv.login(un, pwd);
			TenantMenu(sc);
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	static void TenantMenu(Scanner sc) {

		System.out.println("=========================================================");
		
		int sel=0;
    	do {
    		System.out.println("1 : Get Property List");
    		System.out.println("2 : give offer");
    		System.out.println("3 : Get offer List");
    		System.out.println("4 : Get offer status");
    		System.out.println("5 : Show my agreements");
    		System.out.println("0 : LOG OUT");
    		System.out.print("Enter Selection : ");
    		sel = sc.nextInt();
    		
    		switch(sel) {
			case 1:
				getProperties();
				break;
			case 2:
				giveOffer(sc);
				break;
			case 3:
				getOfferList();
				break;
			case 4:
				getStatus(sc);
				break;
			case 5:
				showAgreements();
				break;
			case 0:
				LoggedInUserId.user=null;
				System.out.println("Logout Successfully");
				break;
			default:
				System.out.println("Invalid Selection, try again");
		}
    	}while(sel!=0);
	}
	
	static void getProperties() {
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		try {
			 serv.getListProperty();
			
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	static void giveOffer(Scanner sc) {
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		System.out.println("Enter Property Id");
		int id=sc.nextInt();
		System.out.println("Enter amount");
		double amount=sc.nextDouble();
		
		try {
			serv.giveOffer(id, amount);
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
		
	}
	
	static void getOfferList() {
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		try {
			 serv.getListOffer();
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	 
	static void getStatus(Scanner sc) {
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		System.out.println("Enter Offer id");
		int id=sc.nextInt();
		
		try {
			Offer p = serv.getOfferStatus(id);
			System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
			
			System.out.println("offerid: "+p.getOfferId()+"		"+"status: "+p.getStatus()+"		"+"myOffer"+p.getOfferAmount()	);
			
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	static void showAgreements() {
		try {
			serv.showmyAgreements();
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
