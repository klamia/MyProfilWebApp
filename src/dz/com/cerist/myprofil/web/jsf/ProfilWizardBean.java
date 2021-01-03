package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class ProfilWizardBean implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 10101111;

	public void save() {        
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + "login");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String onFlowProcess(FlowEvent event) {
            return event.getNewStep();
       
    }
}
