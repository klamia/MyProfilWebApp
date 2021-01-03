package dz.com.cerist.myprofil.web.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


import dz.com.cerist.myprofil.entite.Grade;
import dz.com.cerist.myprofil.service.IGradeService;


@ManagedBean(name = "gradeConverter")
@RequestScoped
public class GradeConverter implements Converter {


		@ManagedProperty(name = "iGradeService", value = "#{iGradeService}")
		private IGradeService iGradeService;
		
		
		public IGradeService getiGradeService() {
			return iGradeService;
		}

		public void setiGradeService(IGradeService iGradeService) {
			this.iGradeService = iGradeService;
		}

		@Override
		public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
			if (submittedValue.trim().equals("")) {
	            return null;
	        } else {
	            try {
	                
	                return iGradeService.getGradebyId(Integer.valueOf(submittedValue));
	                
	               
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
	        	return ((Grade) value).getCodeGrade().toString();
	        }
	        
	    }





}
