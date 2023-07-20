package com.masai.Project;

import java.util.Scanner;

import com.masai.Entity.Owner;

public class OwnerUi {

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
		
		
		
		
	}
}
