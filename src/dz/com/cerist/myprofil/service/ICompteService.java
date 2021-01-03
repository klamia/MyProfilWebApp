package dz.com.cerist.myprofil.service;

import java.util.List;

import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.entite.Profil;
import dz.com.cerist.myprofil.exception.CannotSendMailException;
import dz.com.cerist.myprofil.exception.UserNotFoundException;


public interface ICompteService {

    public  void ajouterCompte(Compte compte);
	
	public  Compte getComptebyId(Integer codeCompte);
	
	public Compte getCompteByLogin(String login);

	public Compte getUserByActionToken(String actionToken) throws UserNotFoundException ;
	
	public Compte getUserByIdentifier(String userIdentifier) throws UserNotFoundException;
	
	public  List<Compte> getAllCompte();
    
    public Compte editerCompte(Compte compte);
	
	public Profil voirProfil(Compte compte);
	
    public void activerCompte(Compte compte);
    
    public void desactiverCompte(Compte compte);
    
    public void initiateResetPassword(Compte compte);

    public void sendPasswordResetMail(Compte compte, String serverString) throws CannotSendMailException ;
    
    public void resetPassword(Compte compte) throws UserNotFoundException;
    
}
