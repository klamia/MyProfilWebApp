package dz.com.cerist.myprofil.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import dz.com.cerist.myprofil.entite.Compte;
import dz.com.cerist.myprofil.entite.Profil;
import dz.com.cerist.myprofil.entite.Role;
import dz.com.cerist.myprofil.service.ICompteService;


@ManagedBean(name = "compteBean")
@SessionScoped
public class CompteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000100;
	@ManagedProperty(name="iCompteService", value="#{iCompteService}")
	private ICompteService iCompteService;
	private Compte compte;
	
	private List<Compte> listcompte ;
    private Compte compteEnCours;
    private Profil profil;
    private List<Role> listroles;
	private Compte editedCompte ;
	private Compte enabledCompte ;
	
	@ManagedProperty(name="compteEnCoursBean", value="#{compteEnCoursBean}")
	private CompteEnCoursBean compteEnCoursBean;

	
	int index;
	
	                                  // Getter and Setter
	
	public ICompteService getiCompteService() {
		return iCompteService;
	}
	public void setiCompteService(ICompteService iCompteService) {
		this.iCompteService = iCompteService;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public List<Compte> getListcompte() {
		
		listcompte=iCompteService.getAllCompte();
		return listcompte;
	}
	public void setListcompte(List<Compte> listcompte) {
		this.listcompte = listcompte;
	}
	public Compte getCompteEnCours() {
		return compteEnCours;
	}
	public void setCompteEnCours(Compte compteEnCours) {
		this.compteEnCours = compteEnCours;
	}
	public Profil getProfil() {
		return profil;
	}
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	public Compte getEditedCompte() {
		return editedCompte;
	}
	public void setEditedCompte(Compte editedCompte) {
		this.editedCompte = editedCompte;
	}
	

	public Compte getEnabledCompte() {
		return enabledCompte;
	}
	public void setEnabledCompte(Compte enabledCompte) {
		this.enabledCompte = enabledCompte;
	}
	
	

	public CompteEnCoursBean getCompteEnCoursBean() {
		return compteEnCoursBean;
	}


	public void setCompteEnCoursBean(CompteEnCoursBean compteEnCoursBean) {
		this.compteEnCoursBean = compteEnCoursBean;
	}
	
	
	public List<Role> getListroles() {
		return listroles;
	}
	public void setListroles(List<Role> listroles) {
		this.listroles = listroles;
	}
	

	
	
	//    Les opérations métiers des boutons
	
	
	@PostConstruct
    public void init() {
        
      
        String login=compteEnCoursBean.getUsername();
        System.out.println(login);
       compte = iCompteService.getCompteByLogin(login);
       System.out.println(compte);
       compteEnCours = compte;
       System.out.println(compteEnCours);
	}
	
	
	
	public void addCompte() {
		compte=new Compte();
		System.out.println("New Compte added, "+compte );
	}
	public void editCompte(Compte compte) {
		System.out.println(" Compte updated");
		index = listcompte.indexOf(compte);
		System.out.println(index);
		editedCompte = compte;	
	}
	public void seeProfilCompte(Compte compte) {
		System.out.println(" Compte a voir le profil");
		
		}
	
		
	public void enabledCompte(Compte compte) {
		System.out.println(" Compte a activer");
		index = listcompte.indexOf(compte);
		enabledCompte = compte;
		} 
	
	
	          
	
	        //  Les services métiers 
	
	
	 public void ajoutCompte() {
		    try{
			System.out.println("AAAAAAAAAAAAAAAAAA");
			iCompteService.ajouterCompte(compte);  
			System.out.println(compte);
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Compte ajouté avec succés"));
	       // RequestContext.getCurrentInstance().reset(":FormCreationCompte:editC:display");
	     //   return "/page.xhtml?faces-redirect=true";
		    
		    }catch(Exception e ){e.printStackTrace();}
	 }
	
	 public void modifierCompte() {
		    try{
			System.out.println("EEEEEEEEEEEEEEEEEE");
			editedCompte= iCompteService.editerCompte(editedCompte);       
			//listcompte.set(index, editedCompte);
		    FacesContext context1 = FacesContext.getCurrentInstance();
	        context1.addMessage(null, new FacesMessage("Compte modifié avec succés"));
	    //RequestContext.getCurrentInstance().reset("editD:display");
	    	   }catch(Exception e ){e.printStackTrace();}
	 }

	 
	 public void modifierCompteCourant() {
		    try{
			System.out.println("CompteCouuuuuuuuuuuurant");
			compteEnCours= iCompteService.editerCompte(compteEnCours);       
			//listcompte.set(index, editedCompte);
		    FacesContext context1 = FacesContext.getCurrentInstance();
	        context1.addMessage(null, new FacesMessage("Compte Courant modifié avec succés"));
	    //RequestContext.getCurrentInstance().reset("editD:display");
	    	   }catch(Exception e ){e.printStackTrace();}
	 }
	 
	 
	 
	 /*
	 public void voirProfil() {
		    
		 if (compteEnCours.getHasProfil()== true){
		 
			System.out.println("PPPPPPPPPPPPPPPP");
			iCompteService.voirProfil(compteEnCours);       
		 }
		 else {
			 FacesContext context = FacesContext.getCurrentInstance();
		     context.addMessage(null, new FacesMessage("Ce compte n'a pas encore de profil"));
		 }
		 }
*/
	 public void activer_desactiver_compte(){
				 
		 if (enabledCompte.getEnabled()==true) {

				System.out.println("DEDEDEDEDEDEDEDEDE");
				iCompteService.desactiverCompte(enabledCompte);
			    FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage("Compte desactivé avec succés"));
		    
		 }
		        else {
		        	System.out.println("ACACACACACACACACACAC");
		        	iCompteService.activerCompte(enabledCompte);       
				
				    FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage("Compte activé avec succés"));
			    
		 
		 
		 }
		    	  
	 }
	 
	 public void reset() {  
	        RequestContext.getCurrentInstance().reset(":FormCreationCompte:editC:display");  
	    }  
	 
}
