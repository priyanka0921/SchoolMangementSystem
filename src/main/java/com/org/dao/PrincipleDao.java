package com.org.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.org.dto.Principle;
import com.org.dto.School;
import com.org.utilities.Helper;

public class PrincipleDao {
	
	
//	public void saveAndUpdate(Principle principle)
//	{
//		EntityManagerFactory emf = Helper.getEMFactory();
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction et = em.getTransaction();
//		
//		 School school = principle.getSchool();
//		
//		et.begin();
//		em.merge(school);
//		et.commit();
//	}
	public void saveAndUpdate(Principle principle) {
	    EntityManagerFactory emf = Helper.getEMFactory();
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    try {
	        et.begin();

	        // Check if principle or school is null before merging
	        if (principle == null || principle.getSchool() == null) {
	            throw new IllegalArgumentException("Principle or associated school cannot be null");
	        }

	        // Merge the school first, then the principle
	        em.merge(principle.getSchool()); // Merge school entity
	        em.merge(principle); // Merge principle entity

	        et.commit();
	    } catch (Exception e) {
	        if (et.isActive()) {
	            et.rollback();
	        }
	        throw e; // Re-throw the exception
	    } finally {
	        em.close();
	    }
	}

	
	public List<Principle> fetchPrincipleByEmailPassword(String email,String password) {
		EntityManagerFactory emf=Helper.getEMFactory();
		EntityManager em = emf.createEntityManager();
		
		String jpql="SELECT p from Principle p where p.email=?1 and p.password=?2";
		Query query = em.createQuery(jpql);
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<Principle> principles=query.getResultList();
		return principles;
	}
	
	public List<Principle> fetchAllPrinciples()
	{
		EntityManagerFactory emf = Helper.getEMFactory();
		EntityManager em = emf.createEntityManager();
		
		String jpql="select s from Principle s";
		
		Query query = em.createQuery(jpql);
		
		List<Principle> principles=query.getResultList();
		return principles;
		
	}
	
	public Principle fetchPrincipleById(int id)
	{
		EntityManagerFactory emf = Helper.getEMFactory();
		EntityManager em = emf.createEntityManager();
		
		 return em.find(Principle.class, id);
	}

}