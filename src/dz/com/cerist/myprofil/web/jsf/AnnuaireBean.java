package dz.com.cerist.myprofil.web.jsf;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;

import dz.com.cerist.myprofil.entite.Profil;
import dz.com.cerist.myprofil.service.IProfilService;




@ManagedBean(name = "annuaireBean")
@SessionScoped
public class AnnuaireBean implements Serializable {

		private static final long serialVersionUID = -9024267082592111181L;

		@ManagedProperty(name="iProfilService", value="#{iProfilService}")
		private IProfilService iProfilService;

		private Profil profil;
		
		private List<Profil> listProfil ;
		
		private List<Profil> listProfilUser ;
		
		private Profil profilCourant ;
		
		private DefaultStreamedContent image;
		
		int index;


		// Getter and Setter
		
		public IProfilService getiProfilService() {
			return iProfilService;
		}


		public void setiProfilService(IProfilService iProfilService) {
			this.iProfilService = iProfilService;
		}


		public Profil getProfil() {
			return profil;
		}


		public void setProfil(Profil profil) {
			this.profil = profil;
		}


		public List<Profil> getListProfil() {
			listProfil=iProfilService.getAllProfil();
			return listProfil;
		}


		public void setListProfil(List<Profil> listProfil) {
			this.listProfil = listProfil;
		}


		


		public List<Profil> getListProfilUser() {
			listProfilUser=iProfilService.getProfilUser();
			return listProfilUser;
		}


		public void setListProfilUser(List<Profil> listProfilUser) {
			this.listProfilUser = listProfilUser;
		}


		public Profil getProfilCourant() {
			return profilCourant;
		}


		public void setProfilCourant(Profil profilCourant) {
			this.profilCourant = profilCourant;
		}


		public DefaultStreamedContent getImage() throws IOException {
	        FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	            //So, browser is requesting the image. Return a real StreamedContent with the image bytes.
	        	/*String profilId  = context.getExternalContext().getRequestParameterMap().get("profilId");
	            Profil profil = iProfilService.getProfilbyId(Integer.valueOf(profilId)); */
	           
	            image = new DefaultStreamedContent(new ByteArrayInputStream(profilCourant.getPhoto()));
	            System.out.println(image);
	           return image;
	        }
	    }
		
		
		
		
		public void setImage(DefaultStreamedContent image) {
			this.image = image;
		}
		 
		
		
		public String apercuProfil(Profil profil) {
			
			System.out.println(" Profil à voir");
			index = listProfilUser.indexOf(profil);
			System.out.println(index);
			profilCourant = profil;	
		
			return "/profil-public?faces-redirect=true";
		}
		
		
		
		
		 
		
       

     
		
		
		
     

	
		
}
