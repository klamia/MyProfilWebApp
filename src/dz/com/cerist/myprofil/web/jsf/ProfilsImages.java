package dz.com.cerist.myprofil.web.jsf;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;


import dz.com.cerist.myprofil.entite.Profil;
import dz.com.cerist.myprofil.service.IProfilService;


@ManagedBean(name = "profilsImages")
@SessionScoped
public class ProfilsImages implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -2042053293822044789L;

	@ManagedProperty(name="iProfilService", value="#{iProfilService}")
	private IProfilService iProfilService;

	private DefaultStreamedContent image;
	
	public DefaultStreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        	String profilId  = context.getExternalContext().getRequestParameterMap().get("profilId");
            Profil profil = iProfilService.getProfilbyId(Integer.valueOf(profilId)); 
           
            image = new DefaultStreamedContent(new ByteArrayInputStream(profil.getPhoto()));
            System.out.println(image);
           return image;
        }
    }
	
	
	
	
	public void setImage(DefaultStreamedContent image) {
		this.image = image;
	}


	public IProfilService getiProfilService() {
		return iProfilService;
	}

	public void setiProfilService(IProfilService iProfilService) {
		this.iProfilService = iProfilService;
	}

	

	



}
