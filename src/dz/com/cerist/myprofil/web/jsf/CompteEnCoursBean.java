package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;






@ManagedBean(name ="compteEnCoursBean")
@SessionScoped
public class CompteEnCoursBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8601729166934092194L;


	public CompteEnCoursBean(){
		
	}
	
	
	private String username;


	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
    
	

	
	public String toString() {
	    return String.format("CompteEnCoursBean[%s]", getUsername() );
	  }
	

}
