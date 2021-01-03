package dz.com.cerist.myprofil.web.jsf;


/*import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;*/
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import dz.com.cerist.myprofil.entite.Sexe;

/*@ManagedBean(name = "sexeConverter")
@RequestScoped*/
@FacesConverter(value="sexeConverter")
public class SexeConverter extends EnumConverter {

	public SexeConverter() {
        super(Sexe.class);
    }

}
