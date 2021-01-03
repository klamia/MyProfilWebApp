package dz.com.cerist.myprofil.service;

import java.util.List;

import dz.com.cerist.myprofil.entite.Division;

public interface IDivisionService {


public  void ajouterDivision(Division division);
	
	public  Division getDivisionbyId(Integer numDivision);
	
	//public  Division getDivisionbyCode(String codeDivision);

	//public  Division getDivisionbyName(String intituleDivision);
	
	public  List<Division> getAllDivision();
    
    public Division editerDivision(Division division);

}
