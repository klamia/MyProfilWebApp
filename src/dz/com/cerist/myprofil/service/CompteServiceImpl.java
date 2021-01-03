package dz.com.cerist.myprofil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.com.cerist.myprofil.dao.ICompteDao;
import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.entite.Profil;
import dz.com.cerist.myprofil.exception.CannotSendMailException;
import dz.com.cerist.myprofil.exception.UserNotFoundException;


@Service("iCompteService")
@Transactional
public class CompteServiceImpl implements ICompteService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4538118853927905086L;
	
	@Autowired
	private ICompteDao iCompteDao;
	
	public ICompteDao getiCompteDao() {
		return iCompteDao;
	}

	public void setiCompteDao(ICompteDao iCompteDao) {
		this.iCompteDao = iCompteDao;
	}

	@Override
	public void ajouterCompte(Compte compte) {
		
		iCompteDao.ajouterCompte(compte);
	}

	@Override
	public Compte getComptebyId(Integer codeCompte) {
		// TODO Auto-generated method stub
		return iCompteDao.getComptebyId(codeCompte);
	}

	@Override
	public List<Compte> getAllCompte() {
		// TODO Auto-generated method stub
		return iCompteDao.getAllCompte();
	}

	@Override
	public Compte editerCompte(Compte compte) {
		// TODO Auto-generated method stub
		return iCompteDao.editerCompte(compte);
	}

	

	@Override
	public Profil voirProfil(Compte compte) {
		// TODO Auto-generated method stub
		return iCompteDao.voirProfil(compte);
	}

	@Override
	public void activerCompte(Compte compte) {
		// TODO Auto-generated method stub
		iCompteDao.activerCompte(compte);
	}

	@Override
	public void desactiverCompte(Compte compte) {
		// TODO Auto-generated method stub
		 iCompteDao.desactiverCompte(compte);
	}

	@Override
	public Compte getCompteByLogin(String login) {
		// TODO Auto-generated method stub
		return iCompteDao.getCompteByLogin(login) ;
	}

	@Override
	public void initiateResetPassword(Compte compte) {
		 iCompteDao.initiateResetPassword(compte);
		
	}

	@Override
	public void sendPasswordResetMail(Compte compte, String serverString)
			throws CannotSendMailException {
		iCompteDao.sendPasswordResetMail(compte, serverString);
		
	}

	@Override
	public void resetPassword(Compte compte) throws UserNotFoundException {
		
		iCompteDao.resetPassword(compte);
	}

	@Override
	public Compte getUserByActionToken(String actionToken) throws UserNotFoundException {
		return iCompteDao.getUserByActionToken(actionToken);
	}

	@Override
	public Compte getUserByIdentifier(String userIdentifier) throws UserNotFoundException {
		return iCompteDao.getUserByIdentifier(userIdentifier);
	}

	
}
