package dz.com.cerist.myprofil.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.entite.Profil;

@Repository("iProfilDao")
@Transactional
public class ProfilDAOimpl implements IProfilDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922565539554717225L;
	@PersistenceContext(unitName = "MyProfilePU")
	private EntityManager em;
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void ajouterProfil(Profil profil) {
		em.persist(profil); 

	}

	@Override
	public Profil getProfilbyId(Integer codeProfil) {
		// TODO Auto-generated method stub
		return em.find(Profil.class, codeProfil );
	}

	@Override
	public Profil getProfilbyName(String nom) {
		// TODO Auto-generated method stub
		return em.find(Profil.class, nom );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profil> getAllProfil() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Profil.findAll").getResultList();
	}

	@Override
	public Profil editerProfil(Profil profil) {
		// TODO Auto-generated method stub
		return em.merge(profil);
	}

	@Override
	public Compte voirCompte(Compte compte) {
		// TODO Auto-generated method stub
		return (Compte) em.createNamedQuery("Profil.voirCompte").setParameter("codeCompte", compte.getCodeCompte()).getSingleResult();
	}

	@Override
	public byte[] loadImage(Integer codeProfil) {
		// TODO Auto-generated method stub
		return   em.find(Profil.class, codeProfil).getPhoto();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profil> getProfilUser() {
		
		//return em.createNamedQuery("Profil.findUserProfil").getResultList();
		return em.createNamedQuery("Profil.findUserProfil").setHint("org.hibernate.cacheable", true).getResultList();
	}

	
	
	/*
	@Override
	public Compte voirCompte(Profil profil) {
		// TODO Auto-generated method stub
		return (Compte) em.createNamedQuery("Profil.voirCompte").setParameter("profil", profil).getSingleResult();
	}
*/
}
