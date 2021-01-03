package dz.com.cerist.myprofil.test;

import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dz.com.cerist.myprofil.entite.Compte;

public class InitDB {

	/**
	 * @param args
	 */
	
	private final static String TABLE_NAME = "compte";
	
	public static void main(String[] args) throws ParseException {
		
		// Unité de persistance
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyProfilePU");
		 // récupérer un EntityManagerFactory à partir de l'unité de persistance
		 EntityManager em = emf.createEntityManager();
		 // début transaction
		 EntityTransaction tx = em.getTransaction();
		 tx.begin();
		 // supprimer les éléments de la table des personnes
		 em.createNativeQuery("delete from " + TABLE_NAME).executeUpdate();
		// créer deux comptes
		  Compte cpt1 = new Compte("kwahiba", "123");
		 
		  Compte cpt2 = new Compte("klamia", "456");
		 // persistance des personnes
		  em.persist(cpt1);
		  em.persist(cpt2);
		  // affichage comptes
		  System.out.println("[comptes]");
		  for (Object cpt : em.createQuery("select cpt from Compte cpt order by cpt.login asc").getResultList())
		 {
		  System.out.println(cpt);
		 }
		  // fin transaction
		  tx.commit();
		  // fin EntityManager
		  em.close();
		  // fin EntityManagerFactory
		  emf.close();
		  // log
		  System.out.println("terminé ...");
		  }
		
		
	}


