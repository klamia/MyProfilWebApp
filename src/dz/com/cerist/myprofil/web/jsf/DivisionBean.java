package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dz.com.cerist.myprofil.entite.Division;

import dz.com.cerist.myprofil.service.IDivisionService;


@ManagedBean(name = "divisionBean")
@SessionScoped
public class DivisionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5166346065639128012L;

	@ManagedProperty(name="iDivisionService", value="#{iDivisionService}")
	private IDivisionService iDivisionService;
	private Division division;
	private List<Division> listdivision ;
	private Division editedDivision ;
	
	
	int index;

	// Getter and Setter
	
	public IDivisionService getiDivisionService() {
		return iDivisionService;
	}


	public void setiDivisionService(IDivisionService iDivisionService) {
		this.iDivisionService = iDivisionService;
	}


	public Division getDivision() {
		return division;
	}


	public void setDivision(Division division) {
		this.division = division;
	}


	public List<Division> getListdivision() {
		listdivision = iDivisionService.getAllDivision();
		return listdivision;
	}


	public void setListdivision(List<Division> listdivision) {
		this.listdivision = listdivision;
	}


	public Division getEditedDivision() {
		return editedDivision;
	}


	public void setEditedDivision(Division editedDivision) {
		this.editedDivision = editedDivision;
	}
	
	  
    
//  Les opérations métiers des boutons
	
	public void addDivision() {
		division=new Division();
		System.out.println("New Division added, "+division );
	}
	public void editDivision(Division division) {
		System.out.println(" Division updated");
		index = listdivision.indexOf(division);
		System.out.println(index);
		editedDivision = division;	
	}

        //  Les services métiers 
	
	
	 public void ajoutDivision() {
		    try{
			System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD");
			iDivisionService.ajouterDivision(division);   
			System.out.println(division);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Division ajoutée avec succés"));
	        //RequestContext.getCurrentInstance().reset(":FormCreationCompte:editC:display");
	    	   }catch(Exception e ){e.printStackTrace();}
	 }
	
	 public void modifierDivision() {
		    try{
			System.out.println("EEEEEEEEEEEEEEEEEE");
			editedDivision= iDivisionService.editerDivision(editedDivision);     
			listdivision.set(index, editedDivision);
		    FacesContext context1 = FacesContext.getCurrentInstance();
	        context1.addMessage(null, new FacesMessage("Division modifiée avec succés"));
	    //RequestContext.getCurrentInstance().reset("editD:display");
	    	   }catch(Exception e ){e.printStackTrace();}
	 }

	 public void reset() {  
	      //  RequestContext.getCurrentInstance().reset("editC:display");  
	    }  
	 


}
