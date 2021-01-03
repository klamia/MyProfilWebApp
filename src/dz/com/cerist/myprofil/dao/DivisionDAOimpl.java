package dz.com.cerist.myprofil.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.com.cerist.myprofil.entite.Division;

@Repository("iDivisionDao")
@Transactional
public class DivisionDAOimpl implements IDivisionDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4155958151878897000L;


	@PersistenceContext(unitName = "MyProfilePU")
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public void ajouterDivision(Division division) {
		// TODO Auto-generated method stub

		em.persist(division);
	}

	@Override
	public Division getDivisionbyId(Integer numDivision) {
		// TODO Auto-generated method stub
		return em.find(Division.class, numDivision);
	}

	/*
	@Override
	public Division getDivisionbyCode(String codeDivision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Division getDivisionbyName(String intituleDivision) {
		// TODO Auto-generated method stub
		return null;
	}
    */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Division> getAllDivision() {
		// TODO Auto-generated method stub
		 return em.createNamedQuery("Division.findAll").getResultList();
	}

	@Override
	public Division editerDivision(Division division) {
		// TODO Auto-generated method stub
		return em.merge(division);
	}

}
