package com.masai.Utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {

	public static EntityManagerFactory emf=Persistence.createEntityManagerFactory("connect");
	
	public static EntityManager getManager() {
		return emf.createEntityManager();
	}
	
	
}
