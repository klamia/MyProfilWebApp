package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import dz.com.cerist.myprofil.entite.Division;
import dz.com.cerist.myprofil.entite.Grade;
import dz.com.cerist.myprofil.entite.Role;
import dz.com.cerist.myprofil.service.IDivisionService;
import dz.com.cerist.myprofil.service.IGradeService;
import dz.com.cerist.myprofil.service.IRoleService;



@ManagedBean(name = "autoCompleteBean")
@RequestScoped
public class AutoCompleteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 10101010;

	private String txt1;

	private String txt2;

	private String txt3;

	private String txt4;

	private String txt5;

	private String txt6;

	private String txt7;

	private Role selectedRole;
	private List<Role> roles;
	private List<SelectItem> selectItemRoles;
	@ManagedProperty(name = "iRoleService", value = "#{iRoleService}")
	private IRoleService iRoleService;
	private List<Role> selectedRoles;
	
	public Role getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(Role selectedRole) {
		this.selectedRole = selectedRole;
	}

	public List<Role> getRoles() {
		roles = iRoleService.findAllroles();
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<SelectItem> getSelectItemRoles() {
		
		getRoles();
		
	       selectItemRoles = new ArrayList<SelectItem>();
			
			for (Role r:roles){
				
				selectItemRoles.add(new SelectItem(r, r.getIntitulerole()));
			}
			
			System.out.println("Size:" +selectItemRoles.size());
		
		return selectItemRoles;
	}

	public void setSelectItemRoles(List<SelectItem> selectItemRoles) {
		this.selectItemRoles = selectItemRoles;
	}

	public IRoleService getiRoleService() {
		return iRoleService;
	}

	public void setiRoleService(IRoleService iRoleService) {
		this.iRoleService = iRoleService;
	}

	public List<Role> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}


/*----------------------------------------------------------------------------------------------------------------------------------------*/
	private Grade selectedGrade;
	private List<Grade> grades;
	private List<SelectItem> selectItemGrades;
	@ManagedProperty(name = "iGradeService", value = "#{iGradeService}")
	private IGradeService iGradeService;
	private List<Grade> selectedGrades;

	
	public Grade getSelectedGrade() {
		return selectedGrade;
	}

	public void setSelectedGrade(Grade selectedGrade) {
		this.selectedGrade = selectedGrade;
	}

	public List<Grade> getGrades() {
		grades = iGradeService.getAllGrade();
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<SelectItem> getSelectItemGrades() {
		
		getGrades();
		
	       selectItemGrades = new ArrayList<SelectItem>();
			
			for (Grade g:grades){
				
				selectItemGrades.add(new SelectItem(g, g.getDesignation()));
			}
			
			System.out.println("Size:" +selectItemGrades.size());
		    return selectItemGrades;
	}

	public void setSelectItemGrades(List<SelectItem> selectItemGrades) {
		this.selectItemGrades = selectItemGrades;
	}

	public IGradeService getiGradeService() {
		return iGradeService;
	}

	public void setiGradeService(IGradeService iGradeService) {
		this.iGradeService = iGradeService;
	}

	public List<Grade> getSelectedGrades() {
		return selectedGrades;
	}

	public void setSelectedGrades(List<Grade> selectedGrades) {
		this.selectedGrades = selectedGrades;
	}

	
	
	
/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/	

	private Division selectedDivision;
	private List<Division> divisions;
	private List<SelectItem> selectItemDivisions;
	@ManagedProperty(name = "iDivisionService", value = "#{iDivisionService}")
	private IDivisionService iDivisionService;
	private List<Division> selectedDivisions;

	
	public Division getSelectedDivision() {
		return selectedDivision;
	}

	public void setSelectedDivision(Division selectedDivision) {
		this.selectedDivision = selectedDivision;
	}

	public List<Division> getDivisions() {
		divisions = iDivisionService.getAllDivision();
		
		
		return divisions;
	}

	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}

	public List<SelectItem> getSelectItemDivisions() {
		getDivisions();
		
	       selectItemDivisions = new ArrayList<SelectItem>();
			
			for (Division d:divisions){
				
				selectItemDivisions.add(new SelectItem(d, d.getIntituleDivision()));
			}
			
			System.out.println("Size:" +selectItemDivisions.size());
		
		
		return selectItemDivisions;
	}

	public void setSelectItemDivisions(List<SelectItem> selectItemDivisions) {
		this.selectItemDivisions = selectItemDivisions;
	}

	public IDivisionService getiDivisionService() {
		return iDivisionService;
	}

	public void setiDivisionService(IDivisionService iDivisionService) {
		this.iDivisionService = iDivisionService;
	}

	public List<Division> getSelectedDivisions() {
		return selectedDivisions;
	}

	public void setSelectedDivisions(List<Division> selectedDivisions) {
		this.selectedDivisions = selectedDivisions;
	}

	

	
/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	

	
/*---------------------------------------------------------------------------------------------------------------------------------------------*/

       

	private List<String> selectedTexts;
       public List<String> getSelectedTexts() {
   		return selectedTexts;
   	}
       public void setSelectedTexts(List<String> selectedTexts) {
   		this.selectedTexts = selectedTexts;
   	}

	   public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}

		return results;
	}

	public List<Role> completeRole(String query) {
		List<Role> suggestions = new ArrayList<Role>();

		for (Role r : roles) {
			if (r.getIntitulerole().startsWith(query))
				suggestions.add(r);
		}

		return suggestions;
		
	}
	

	public List<Grade> completeGrade(String query) {
		List<Grade> suggestions2 = new ArrayList<Grade>();

		for (Grade g : grades) {
			if (g.getDesignation().startsWith(query))
				suggestions2.add(g);
		}

		return suggestions2;
		
	}
	
	
	
	public List<String> completeArea(String query) {
		List<String> results = new ArrayList<String>();

		if (query.equals("PrimeFaces")) {
			results.add("PrimeFaces Rocks!!!");
			results.add("PrimeFaces has 100+ components.");
			results.add("PrimeFaces is lightweight.");
			results.add("PrimeFaces is easy to use.");
			results.add("PrimeFaces is developed with passion!");
		} else {
			for (int i = 0; i < 10; i++) {
				results.add(query + i);
			}
		}

		return results;
	}

	public void handleSelect(SelectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Selected:" + event.getObject().toString(), null);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void handleUnselect(UnselectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Unselected:" + event.getObject().toString(), null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}

	public String getTxt2() {
		return txt2;
	}

	public void setTxt2(String txt2) {
		this.txt2 = txt2;
	}

	public String getTxt3() {
		return txt3;
	}

	public void setTxt3(String txt3) {
		this.txt3 = txt3;
	}

	public String getTxt4() {
		return txt4;
	}

	public void setTxt4(String txt4) {
		this.txt4 = txt4;
	}

	public String getTxt5() {
		return txt5;
	}

	public void setTxt5(String txt5) {
		this.txt5 = txt5;
	}

	public String getTxt6() {
		return txt6;
	}

	public void setTxt6(String txt6) {
		this.txt6 = txt6;
	}

	public String getTxt7() {
		return txt7;
	}

	public void setTxt7(String txt7) {
		this.txt7 = txt7;
	}

	

	
}
