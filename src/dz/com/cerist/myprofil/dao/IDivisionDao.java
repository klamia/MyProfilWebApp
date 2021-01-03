package dz.com.cerist.myprofil.dao;

import java.util.List;

import dz.com.cerist.myprofil.entite.Division;


public interface IDivisionDao {

    public  void ajouterDivision(Division division);
	
	public  Division getDivisionbyId(Integer numDivision);
	
	//public  Division getDivisionbyCode(String codeDivision);

	//public  Division getDivisionbyName(String intituleDivision);
	
	public  List<Division> getAllDivision();
    
    public Division editerDivision(Division division);

}
