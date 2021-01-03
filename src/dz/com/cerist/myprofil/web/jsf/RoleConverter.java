package dz.com.cerist.myprofil.web.jsf;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import dz.com.cerist.myprofil.entite.Role;
import dz.com.cerist.myprofil.service.IRoleService;


@ManagedBean(name = "roleConverter")
@RequestScoped
public class RoleConverter implements Converter {

	
	/**
	 * 
	 */
	
	@ManagedProperty(name = "iRoleService", value = "#{iRoleService}")
	private IRoleService iRoleService;
	
		public IRoleService getiRoleService() {
		return iRoleService;
	}

	public void setiRoleService(IRoleService iRoleService) {
		this.iRoleService = iRoleService;
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                
                return iRoleService.findroleById(Integer.valueOf(submittedValue));
                
               
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid role"));
            }
        }

       
       
    }
	
	@Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
            return "";
        } else {
        	return ((Role) value).getCoderole().toString();
        }
        
    }

}
