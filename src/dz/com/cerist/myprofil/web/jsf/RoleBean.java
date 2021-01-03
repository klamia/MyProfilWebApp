package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;
import java.util.List;





import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dz.com.cerist.myprofil.entite.Role;
import dz.com.cerist.myprofil.service.IRoleService;


@ManagedBean(name= "roleBean")
@SessionScoped
public class RoleBean implements Serializable {

private static final long serialVersionUID = 111140;


@ManagedProperty(name="iRoleService", value="#{iRoleService}")
private IRoleService iRoleService;
private Role roles;
private List<Role> listRole ;
private Role editRole ;
private Role deletedRole ;
int index;



public IRoleService getiRoleService() {
	return iRoleService;
}



public void setiRoleService(IRoleService iRoleService) {
	this.iRoleService = iRoleService;
}



public Role getRoles() {
	return roles;
}



public void setRoles(Role roles) {
	this.roles = roles;
}



public List<Role> getListRole() {
	
		listRole = iRoleService.findAllroles();
	
	return listRole;
}


public void setListRole(List<Role> listRole) {
	this.listRole = listRole;
}



public Role getEditRole() {
	return editRole;
}



public void setEditRole(Role editRole) {
	this.editRole = editRole;
}



public Role getDeletedRole() {
	return deletedRole;
}



public void setDeletedRole(Role deletedRole) {
	this.deletedRole = deletedRole;
}


public void addRole() {
	System.out.println("New Roles add");
	roles = new Role();

}

public void editRole(Role role) {
	System.out.println(" Roles updated");
	index = listRole.indexOf(role);
	editRole = role;
	

}

public void supprimeRole(Role role) {
	System.out.println(" Roles deleted");
	index = listRole.indexOf(role);
	deletedRole = role;
	

}


public void updateRole() {
	
	
	editRole = iRoleService.merge(editRole) ;  
	listRole.set(index, editRole);
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Role mise à jour avec succés"));
}

public void ajoutRole() {
	try{
		System.out.println("FFFFFFFFFFFFFFF");
    iRoleService.persist(roles);       
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Role ajouté avec succés"));
  
}catch(Exception e ){e.printStackTrace();}
}

public void deleteRole() {
	
	deletedRole  = iRoleService.remove(deletedRole);
	listRole.set(index, deletedRole);
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("Role supprimé avec succés"));
	
    
}


}

