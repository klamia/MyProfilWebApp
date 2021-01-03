package dz.com.cerist.myprofil.dao;

import java.util.List;


import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.entite.Profil;

public interface IProfilDao {

    public  void ajouterProfil(Profil profil);
	
	public  Profil getProfilbyId(Integer codeProfil);
	
	public  Profil getProfilbyName(String nom);

    public  List<Profil> getAllProfil();
    
    public  List<Profil> getProfilUser();
    
    public Profil editerProfil(Profil profil);
    
  //  public Compte voirCompte(Profil profil);
    public Compte voirCompte(Compte compte);
	
    public byte[] loadImage(Integer codeProfil);
    
	
}
