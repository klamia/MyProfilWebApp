package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;



@ManagedBean(name = "logoutBean")
@SessionScoped
public class LogoutBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3568763907831384992L;

	/**
	 * 
	 */
	

	public String logout(){
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "/login?faces-redirect=true";
	}
	
	
}

