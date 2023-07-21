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
		System.out.println("ENter Username");
		String un=sc.next();
		System.out.println("Enter Password");
		String pwd=sc.next();
		System.out.println("Enter FullName");
		String fn=sc.next();
		System.out.println("Enter Contact ");
		String con=sc.next();
		
		Owner owner=new Owner(un,pwd,fn,con);
		
		try {
			serv.register(owner);
		} catch (SomethingWentWrongEx e) {
			e.printStackTrace();
		}	
	}
	
	static void login(Scanner sc) {
		System.out.println("ENter Username");
		String un=sc.next();
		System.out.println("Enter Password");
		String pwd=sc.next();
		
		try {
			serv.login(un, pwd);
			System.out.println("Login Successfully");
			ownerMenu(sc);
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	
	static void ownerMenu(Scanner sc) {
		int choice=0;
		do {
			System.out.println("1 : Add property");
			System.out.println("2 : Update Property");
			System.out.println("3 : get Tenant List");
			System.out.println("0 : LOGOUT");
			System.out.print("Enter option : ");
			choice =sc.nextInt();
			
			switch(choice) {
			case 1:
				OwnerUi.addProperty(sc);
				break;
			case 2:
				OwnerUi.updateProperty(sc);
				break;
			case 3:
				OwnerUi.getListRenter();
				break;
			case 0:
				LoggedInUserId.user=null;
				System.out.println("logout successfully");
				break;
			default:
				System.out.println("invalid option");
			}
		}while(choice!=0);
	}
	static void addProperty(Scanner sc) {
		System.out.println("Enter location");
		String loc=sc.next();
		System.out.println("Enter amount");
		double amount=sc.nextDouble();
		System.out.println("Enter no. of bedrooms");
		int num=sc.nextInt();
		
		try {
			serv.addProperty(loc, amount, num);
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
		
	}
	
	static void updateProperty(Scanner sc) {
		System.out.println("Enter Property Id");
		int id=sc.nextInt();
		System.out.println("Enter location");
		String loc=sc.next();
		System.out.println("Enter amount");
		double amount=sc.nextDouble();
		
		try {
			serv.updateProperty(id, loc, amount);
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	static void getListRenter() {
		try {
			List<Tenant> list = serv.getListRenter();
			list.forEach(p->p.getFullName());
			
		} catch (SomethingWentWrongEx | NoRecordFoundEx e) {
			e.printStackTrace();
		}
	}
	
	
	
}
