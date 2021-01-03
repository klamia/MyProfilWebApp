package dz.com.cerist.myprofil.web.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import dz.com.cerist.myprofil.entite.Division;
import dz.com.cerist.myprofil.service.IDivisionService;


@ManagedBean(name = "divisionConverter")
@RequestScoped
public class DivisionConverter implements Converter {

	@ManagedProperty(name = "iDivisionService", value = "#{iDivisionService}")
	private IDivisionService iDivisionService;
	
	
	public IDivisionService getiDivisionService() {
		return iDivisionService;
	}

	public void setiDivisionService(IDivisionService iDivisionService) {
		this.iDivisionService = iDivisionService;
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                
                return iDivisionService.getDivisionbyId(Integer.valueOf(submittedValue));
                
               
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid grade"));
            }
        }

       
       
    }
	
	@Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
            return "";
        } else {
        	return ((Division) value).getNumDivision().toString();      
        }
        
    }






}
