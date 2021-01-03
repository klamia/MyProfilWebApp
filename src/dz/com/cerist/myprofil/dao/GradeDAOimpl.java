package dz.com.cerist.myprofil.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dz.com.cerist.myprofil.entite.Grade;

@Repository("iGradeDao")
@Transactional
public class GradeDAOimpl implements IGradeDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2225120433048811930L;
	@PersistenceContext(unitName = "MyProfilePU")
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void ajouterGrade(Grade grade) {
		em.persist(grade);

	}

	@Override
	public Grade getGradebyId(Integer codeGrade) {
		// TODO Auto-generated method stub
		 return em.find(Grade.class, codeGrade);
	}

	@Override
	public Grade getGradebyName(String designation) {
		// TODO Auto-generated method stub
		return em.find(Grade.class, designation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> getAllGrade() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Grade.findAll").getResultList();
	}

	@Override
	public Grade editerGrade(Grade grade) {
		// TODO Auto-generated method stub
		return em.merge(grade);
	}

}
