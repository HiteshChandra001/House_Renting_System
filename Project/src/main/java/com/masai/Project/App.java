package com.masai.Project;

import java.util.Scanner;

public class App {
	
    public static void main( String[] args ) {
    	Scanner sc=new Scanner(System.in);
    	int sel=0;
    	do {
    		System.out.println("=========================================================");
    		System.out.println("1 : Owner Registration");
    		System.out.println("2 : Owner Login");
    		System.out.println("3 : Tenant Registration");
    		System.out.println("4 : Tenant Login");
    		System.out.println("0 : Exit");
    		System.out.print("Enter Selection : ");
<<<<<<< HEAD
    		choice = sc.nextInt();

    		switch(choice) {
=======
    		sel = sc.nextInt();
    		
    		switch(sel) {
>>>>>>> de74c1fde4168dd12738087e8e355d2e94290cfa
			case 1:
				OwnerUi.registration(sc);
				break;
			case 2:
				OwnerUi.login(sc);
				break;
			case 3:
				TenantUi.registration(sc);
				break;
			case 4:
				TenantUi.login(sc);
				break;
			case 0:
				System.out.println("Thanks for using the services.");
				break;
			default:
				System.out.println("Invalid Selection, try again");
		}
    	}while(sel!=0);
        
    	sc.close();
    }
}
