package dz.com.cerist.myprofil.web.jsf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;


import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;




import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;




import dz.com.cerist.myprofil.entite.Compte;

import dz.com.cerist.myprofil.entite.Profil;


import dz.com.cerist.myprofil.service.ICompteService;
import dz.com.cerist.myprofil.service.IProfilService;


@ManagedBean(name = "profilBean")
@SessionScoped
public class ProfilBean implements Serializable {

		private static final long serialVersionUID = -9024267082592111181L;

		@ManagedProperty(name="iProfilService", value="#{iProfilService}")
		private IProfilService iProfilService;
		
		@ManagedProperty(name="iCompteService", value="#{iCompteService}")
		private ICompteService iCompteService;
		
		@ManagedProperty(name="compteEnCoursBean", value="#{compteEnCoursBean}")
		private CompteEnCoursBean compteEnCoursBean;
		
		
		private Profil profil;
		
		private List<Profil> listProfil ;
	    private Profil profilEnCours;
	    private Profil profilCourant ;
	    private Compte compte;
	    private Compte editedCompte;
	    
	    /*
	    private Grade grade;
	    private List<Grade> listgrades;
	    */
		private Profil editedProfil ;
		private Profil enabledProfil ;
       
		private UploadedFile uploadedFile;
		private UploadedFile uploadedFile2;
		private UploadedFile pdfFile;
		private UploadedFile pdfFile1;
		
		private DefaultStreamedContent myPhoto;
		private DefaultStreamedContent imagePhoto;
		
		private DefaultStreamedContent photoIdent;
		private Profil createdProfil;
		
		int index;

		
		 

		@PostConstruct
	    public void init() {
	        /*
			// In case you're updating an existing entity.
	        entity = entityService.getById(entityId);
            */
	        // Or in case you want to create a new entity.
	        profil = new Profil();
	       
	       /*profilEnCours = new Profil();*/
	     /* editedProfil=  new Profil();*/
	      
	    //    System.out.println(compte_en_cours);
	      
	        String login=compteEnCoursBean.getUsername();
	        System.out.println(login);
	       compte = iCompteService.getCompteByLogin(login);
	       System.out.println(compte);
	       
	       profil.setCompte(compte);
	      
	  
	       
	       editedProfil = compte.getProfil();
		   System.out.println(editedProfil);
		  
		//   editedCompte =compte;
		
		   System.out.println(myPhoto);
		   /* editedProfil=profilEnCours;
		  System.out.println(editedProfil);*/
		}

		
		
		
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


		public Profil getProfilEnCours() {
			return profilEnCours;
		}


		public void setProfilEnCours(Profil profilEnCours) {
			this.profilEnCours = profilEnCours;
		}


		public Compte getCompte() {
			return compte;
		}


		public void setCompte(Compte compte) {
			
			this.compte = compte;
		}

		
		
		
		


		public Compte getEditedCompte() {
			return editedCompte;
		}




		public void setEditedCompte(Compte editedCompte) {
			this.editedCompte = editedCompte;
		}




		/*
		
		public Grade getGrade() {
			return grade;
		}

		public void setGrade(Grade grade) {
			this.grade = grade;
		}


		public List<Grade> getListgrades() {
			return listgrades;
		}


		public void setListgrades(List<Grade> listgrades) {
			this.listgrades = listgrades;
		}

*/
		public Profil getEditedProfil() {
			return editedProfil;
		}


		public void setEditedProfil(Profil editedProfil) {
			this.editedProfil = editedProfil;
		}


		public Profil getProfilCourant() {
			return profilCourant;
		}




		public void setProfilCourant(Profil profilCourant) {
			this.profilCourant = profilCourant;
		}




		public Profil getEnabledProfil() {
			return enabledProfil;
		}


		public void setEnabledProfil(Profil enabledProfil) {
			this.enabledProfil = enabledProfil;
		}
		
		public ICompteService getiCompteService() {
			return iCompteService;
		}


		public void setiCompteService(ICompteService iCompteService) {
			this.iCompteService = iCompteService;
		}

		

	
		public CompteEnCoursBean getCompteEnCoursBean() {
			return compteEnCoursBean;
		}




		public void setCompteEnCoursBean(CompteEnCoursBean compteEnCoursBean) {
			this.compteEnCoursBean = compteEnCoursBean;
		}


		
		public UploadedFile getUploadedFile() {
			return uploadedFile;
		}




		public void setUploadedFile(UploadedFile uploadedFile) {
			this.uploadedFile = uploadedFile;
		}




		public DefaultStreamedContent getMyPhoto() {
			FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	           
	           
	        	myPhoto = new DefaultStreamedContent(new ByteArrayInputStream(profil.getPhoto()));
	            System.out.println(myPhoto);
	        	
        	return myPhoto;
	        }
		}




		public void setMyPhoto(DefaultStreamedContent myPhoto) {
			this.myPhoto = myPhoto;
		}



		public UploadedFile getUploadedFile2() {
			return uploadedFile2;
		}




		public void setUploadedFile2(UploadedFile uploadedFile2) {
			this.uploadedFile2 = uploadedFile2;
		}



	

		public void setPdfUploadedFile(UploadedFile pdfFile) {
			this.pdfFile = pdfFile;
		}


		
		public DefaultStreamedContent getImagePhoto() {
			return imagePhoto;
		}




		public void setImagePhoto(DefaultStreamedContent imagePhoto) {
			this.imagePhoto = imagePhoto;
		}


		/* 
		public void editProfil(Profil profil) {
			/*
			System.out.println(" Profil updated");
			index = listProfil.indexOf(profil);
			System.out.println(index);
			editedProfil = profil;	
		 
		
		}*/
		
        public DefaultStreamedContent getPhotoIdent() {
        	FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	           
	           
	        	photoIdent = new DefaultStreamedContent(new ByteArrayInputStream(createdProfil.getPhoto()));
	            System.out.println(photoIdent);
	        	
        	return photoIdent;
	        }
	   }




		public void setPhotoIdent(DefaultStreamedContent photoIdent) {
			this.photoIdent = photoIdent;
		}




		public Profil getCreatedProfil() {
			return createdProfil;
		}




		public void setCreatedProfil(Profil createdProfil) {
			this.createdProfil = createdProfil;
		}




		public String apercuProfil(Profil profil) {
			
			System.out.println(" Profil à voir");
			index = listProfil.indexOf(profil);
			System.out.println(index);
			profilCourant = profil;	
		
			return "/user-pages/profil-professionnel?faces-redirect=true";
		}
		
		
		
		public UploadedFile getPdfFile() {
			return pdfFile;
		}
		

		public void setPdfFile(UploadedFile pdfFile) {
			this.pdfFile = pdfFile;
		}




		public UploadedFile getPdfFile1() {
			return pdfFile1;
		}




		public void setPdfFile1(UploadedFile pdfFile1) {
			this.pdfFile1 = pdfFile1;
		}


		 //  Les services métiers 

		  /* Upload methods for Images */
		
		public void handleFileUpload(FileUploadEvent event) throws IOException {

			//setMyPhoto(null);
			uploadedFile = event.getFile();
		    System.out.println(uploadedFile.getFileName());
		   /* editedProfil = compte.getProfil();*/
		    
		    byte[] foto = IOUtils.toByteArray(uploadedFile.getInputstream());
		    System.out.println(foto);
		    profil.setPhoto(foto);
		   
		    /*
		    ByteArrayInputStream img = new ByteArrayInputStream(foto);
		    myPhoto = new DefaultStreamedContent(img,"image/png, jpg, jpeg, gif");*/
		   setUploadedFile(null);
		    
		   
		}
		
       public void handleFileUploadSecond(FileUploadEvent event) throws IOException {

    	    
    	  // setImagePhoto(null);
			uploadedFile2 = event.getFile();
		    System.out.println(uploadedFile2.getFileName());
		 
		    
		    byte[] foto2 = IOUtils.toByteArray(uploadedFile2.getInputstream());
		    System.out.println(foto2);
		    
		    editedProfil.setPhoto(foto2);
		   
		   
		    
		    ByteArrayInputStream img = new ByteArrayInputStream(foto2);
		    imagePhoto = new DefaultStreamedContent(img,"image/png, jpg, jpeg, gif");
		   
		  
		    setUploadedFile2(null);
		    
		  System.out.println(foto2);
		}

     
       
                         /* Upload methods for PDF files 
       
       public void handleFileUploadEdited(FileUploadEvent event) throws IOException {
    
    	   pdfFile = event.getFile();
    	   String fileName = FilenameUtils.getName(pdfFile.getFileName());  
           System.out.println(FilenameUtils.getExtension(fileName));  
           System.out.println(pdfFile.getFileName());  
           String contentType = FacesContext.getCurrentInstance()  
                     .getExternalContext().getMimeType(fileName);
   		
   		
   		byte[] file = pdfFile.getContents();
   		System.out.println(file);
   		editedProfil.setCv(file);
   		
   		    FacesContext.getCurrentInstance().addMessage(null, 
              new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType )));
          
   		   setPdfFile(null);  
          }
		
		
		
       public void handleFileUploadAdded(FileUploadEvent event) throws IOException {
    	    
    	   pdfFile1 = event.getFile();
    	   String fileName1 = FilenameUtils.getName(pdfFile1.getFileName());  
           System.out.println(FilenameUtils.getExtension(fileName1));  
           System.out.println(pdfFile1.getFileName());  
           String contentType1 = FacesContext.getCurrentInstance()  
                     .getExternalContext().getMimeType(fileName1);
   		
   		
   		byte[] file1 = pdfFile1.getContents();
   		System.out.println(file1);
   		profil.setCv(file1);
   		
   		    FacesContext.getCurrentInstance().addMessage(null, 
              new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName1, contentType1 )));
          
   		   setPdfFile(null);  
          }
*/


		public String ajoutProfil() {
	    try{
		System.out.println("AAAAAAAAAAAAAAAAAA");
		System.out.println(profil);
		iProfilService.ajouterProfil(profil);
		createdProfil=profil;
		//editedProfil=profil;
		setMyPhoto(null);
		System.out.println(profil);
		Compte compte =profil.getCompte();
		compte.setHasProfil(true);
		compte.setProfil(profil);
		iCompteService.editerCompte(compte);
		
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Profil ajouté avec succés"));
        //RequestContext.getCurrentInstance().reset(":FormCreationCompte:editC:display");
    	   }catch(Exception e ){e.printStackTrace();}
		return "/user-pages/profilCreated?faces-redirect=true";
        }
		
		
		
		public String onFlowProcess(FlowEvent event) {
	            return event.getNewStep();
	       
	    }


		public void modifierProfil() {
		    try{
			System.out.println("EEEEEEEEEEEEEEEEEE");
			editedProfil= iProfilService.editerProfil(editedProfil);
			setImagePhoto(null);
			/*listProfil.set(index, editedProfil);*/
		    FacesContext context1 = FacesContext.getCurrentInstance();
	        context1.addMessage(null, new FacesMessage("Profil modifié avec succés"));
	    //RequestContext.getCurrentInstance().reset("editD:display");
	    	   }catch(Exception e ){e.printStackTrace();}
	 }

		
		
		
		public void modifierCompteProfil(){
			try{
			System.out.println("CPCPCPCPCPCPCP");
			
			iCompteService.editerCompte(editedCompte);
			/*setImagePhoto(null);*/
			/*listProfil.set(index, editedProfil);*/
		    FacesContext context1 = FacesContext.getCurrentInstance();
	        context1.addMessage(null, new FacesMessage("Profil modifié avec succés"));
	    //RequestContext.getCurrentInstance().reset("editD:display");
	    	   }catch(Exception e ){e.printStackTrace();}
		
		}
	
		
}
