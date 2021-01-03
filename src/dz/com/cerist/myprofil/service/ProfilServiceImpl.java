package dz.com.cerist.myprofil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import dz.com.cerist.myprofil.dao.IProfilDao;
import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.entite.Profil;

@Transactional
@Service("iProfilService")
public class ProfilServiceImpl implements IProfilService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2200046831077246234L;
	
	@Autowired
	private IProfilDao iProfilDao;
	
	public IProfilDao getiProfilDao() {
		return iProfilDao;
	}

	public void setiProfilDao(IProfilDao iProfilDao) {
		this.iProfilDao = iProfilDao;
	}

	
	@Override
	public void ajouterProfil(Profil profil) {
		// TODO Auto-generated method stub
		iProfilDao.ajouterProfil(profil);
	}

	@Override
	public Profil getProfilbyId(Integer codeProfil) {
		// TODO Auto-generated method stub
		return iProfilDao.getProfilbyId(codeProfil);
	}

	@Override
	public Profil getProfilbyName(String nom) {
		// TODO Auto-generated method stub
		return iProfilDao.getProfilbyName(nom);
	}

	@Override
	public List<Profil> getAllProfil() {
		// TODO Auto-generated method stub
		return iProfilDao.getAllProfil();
	}

	@Override
	public Profil editerProfil(Profil profil) {
		// TODO Auto-generated method stub
		return iProfilDao.editerProfil(profil);
	}

	@Override
	public Compte voirCompte(Compte compte) {
		// TODO Auto-generated method stub
		return iProfilDao.voirCompte(compte);
	}

	@Override
	public byte[] loadImage(Integer codeProfil) {
		// TODO Auto-generated method stub
		return iProfilDao.loadImage(codeProfil);
	}

	@Override
	public List<Profil> getProfilUser() {
		// TODO Auto-generated method stub
		return iProfilDao.getProfilUser() ;
	}

	
	
	
/*
	@Override
	public Compte voirCompte(Profil profil) {
		// TODO Auto-generated method stub
		return iprofildao.voirCompte(profil);
	}
*/
	

	
	
}
