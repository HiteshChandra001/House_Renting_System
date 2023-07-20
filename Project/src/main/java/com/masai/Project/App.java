package com.masai.Project;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	
	
	
	
	
	
    public static void main( String[] args ) {
    	Scanner sc=new Scanner(System.in);
    	int choice=0;
    	do {
    		System.out.println("1 : Owner Registration");
    		System.out.println("2 : Owner Login");
    		System.out.println("3 : Tenant Registration");
    		System.out.println("4 : Tenant Login");
    		System.out.println("0 : Exit");
    		System.out.print("Enter Selection : ");
    		choice = sc.nextInt();
    		
    		switch(choice) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 0:
				System.out.println("Thanks for using the services");
				break;
			default:
				System.out.println("Invalid Selection, try again");
		}
    	}while(choice!=0);
        
    	sc.close();
    }
}
