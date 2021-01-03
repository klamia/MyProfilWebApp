package dz.com.cerist.myprofil.dao;

import java.util.List;

import dz.com.cerist.myprofil.entite.Grade;


public interface IGradeDao {

    public  void ajouterGrade(Grade grade);
	
	public  Grade getGradebyId(Integer codeGrade);
	
	public  Grade getGradebyName(String designation);

    public  List<Grade> getAllGrade();
    
    public Grade editerGrade(Grade grade);
	
}
