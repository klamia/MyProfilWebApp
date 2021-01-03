package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import dz.com.cerist.myprofil.entite.Grade;

import dz.com.cerist.myprofil.service.IGradeService;




@ManagedBean(name = "gradeBean")
@SessionScoped
public class GradeBean implements Serializable {

	private static final long serialVersionUID = 1316532732275964608L;

	@ManagedProperty(name="iGradeService", value="#{iGradeService}")
	private IGradeService iGradeService;
	private Grade grade;
	private List<Grade> listgrade ;
	private Grade editedGrade ;
	
	
	int index;
	
	  // Getter and Setter
	
	public IGradeService getiGradeService() {
		return iGradeService;
	}
	public void setiGradeService(IGradeService iGradeService) {
		this.iGradeService = iGradeService;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public List<Grade> getListgrade() {
		
		listgrade=iGradeService.getAllGrade();
		return listgrade;
	}
	public void setListgrade(List<Grade> listgrade) {
		this.listgrade = listgrade;
	}
	public Grade getEditedGrade() {
		return editedGrade;
	}
	public void setEditedGrade(Grade editedGrade) {
		this.editedGrade = editedGrade;
	}
	
	
	
	
//  Les opérations métiers des boutons
	
	public void addGrade() {
		grade=new Grade();
		System.out.println("New Grade added, "+grade );
	}
	public void editGrade(Grade grade) {
		System.out.println(" Grade updated");
		index = listgrade.indexOf(grade);
		System.out.println(index);
		editedGrade = grade;	
	}
	
//  Les services métiers 
	
	
	 public void ajoutGrade() {
		    try{
			System.out.println("GGGGGGGGGGGGGGGGGG");
			iGradeService.ajouterGrade(grade);   
			System.out.println(grade);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Grade ajouté avec succés"));
	        //RequestContext.getCurrentInstance().reset(":FormCreationCompte:editC:display");
	    	   }catch(Exception e ){e.printStackTrace();}
	 }
	
	 public void modifierGrade() {
		    try{
			System.out.println("EEEEEEEEEEEEEEEEEE");
			editedGrade= iGradeService.editerGrade(editedGrade);     
			//listcompte.set(index, editedCompte);
		    FacesContext context1 = FacesContext.getCurrentInstance();
	        context1.addMessage(null, new FacesMessage("Grade modifié avec succés"));
	    //RequestContext.getCurrentInstance().reset("editD:display");
	    	   }catch(Exception e ){e.printStackTrace();}
	 }

	 public void reset() {  
	      //  RequestContext.getCurrentInstance().reset("editC:display");  
	    }  
	 
	
}
