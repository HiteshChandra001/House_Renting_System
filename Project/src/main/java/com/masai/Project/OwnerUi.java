package com.masai.Project;

import java.util.List;
import java.util.Scanner;

import com.masai.Entity.LoggedInUserId;
import com.masai.Entity.Owner;
import com.masai.Entity.Property;
import com.masai.Entity.Tenant;
import com.masai.Exception.NoRecordFoundEx;
import com.masai.Exception.SomethingWentWrongEx;
import com.masai.Service.OwnerService;
import com.masai.Service.OwnerServiceImpl;

public class OwnerUi {

	static OwnerService serv=new OwnerServiceImpl();

	static void registration(Scanner sc) {
		
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		System.out.println("ENter Username");
		String un=sc.next();
		System.out.println("Enter Password");
		String pwd=sc.next();
		sc.nextLine();
		System.out.println("Enter FullName");
		String fn=sc.nextLine();
		System.out.println("Enter Contact ");
		String con=sc.next();
		
		Owner owner=new Owner(un,pwd,fn,con);
		try {
			serv.register(owner);
			System.out.println("Owner Registered SUccessfully");
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

			System.out.println("Logged in SUccessfully");
			OwnerMenu(sc);
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	static void OwnerMenu(Scanner sc) {
		

		System.out.println("=========================================================");
		
		int sel=0;
    	do {
    		System.out.println("1 : Add Property");
    		System.out.println("2 : Update Property");
    		System.out.println("3 : Get Property List");
    		System.out.println("4 : Get Agreemets List");
    		System.out.println("5 : show offer list of property");
    		System.out.println("6 : Accept Offer");
    		
    		System.out.println("0 : LOG OUT");
    		System.out.print("Enter Selection : ");
    		sel = sc.nextInt();
    		
    		switch(sel) {
			case 1:
				addProperty(sc);
				break;
			case 2:
				updateProperty(sc);
				break;
			case 3:
				getPropertyList();
				break;
			case 4:
				showmyAgreements();
				break;
			case 5:
				showOfferList(sc);
				break;
			case 6:
				acceptOffer(sc);
				break;
			case 0:
				LoggedInUserId.user=null;
				System.out.println("Logout Successfull");
				break;
			default:
				System.out.println("Invalid Selection, try again");
		}
    	}while(sel!=0);
    	
	}
	
	static void addProperty(Scanner sc) {

		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		System.out.println("Enter Location");
		String loc=sc.next();
		System.out.println("Enter amount");
		double amount=sc.nextDouble();
		System.out.println("Enter no. of Bedrooms");
		int room=sc.nextInt();
		
		try {
			serv.addProperty(loc, amount, room);
			System.out.println("Property Added SUccessfully");
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
		
	}
	
	static void updateProperty(Scanner sc) {
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		System.out.println("Enter Property Id");
		int id=sc.nextInt();
		System.out.println("Enter Location");
		String loc=sc.next();
		System.out.println("Enter amount");
		double amount=sc.nextDouble();
		System.out.println("Enter no of Rooms");
		int room=sc.nextInt();
		
		try {
			serv.updateProperty(id,loc, amount,room);

			System.out.println("Property updated SUccessfully");
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
		
	}
	
	static void getPropertyList() {
		System.out.println("My Properties list :-");
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
	
		try {
			serv.getListProperty();
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}	
	}
	
	static void showOfferList(Scanner sc) {
		System.out.println("enter property id");
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
	
		int id=sc.nextInt();
		System.out.println(" Offers list :-");
		try {
			serv.showOfferList(id);
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}	
	}
	 
	static void showmyAgreements() {
		System.out.println("Agreement list :-");
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-++-++-+-+-+-+-+-+-+-+-+-+-+-+-");
		
		try {
			serv.showmyAgreements();
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}

	static void acceptOffer(Scanner sc) {
		System.out.println("Enter offerid");
		int id=sc.nextInt();
		
		try {
			serv.acceptOffer(id);
			System.out.println("Offer accepted Successfully");
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	
}
