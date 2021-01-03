package dz.com.cerist.myprofil.entite;

import java.io.Serializable;
import javax.persistence.*;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;


/**
 * The persistent class for the profil database table.
 * 
 */
@Entity
@Cacheable(true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="profil")
@NamedQueries({
@NamedQuery(name = "Profil.findAll", query = "SELECT pfl FROM Profil pfl order by pfl.nom  "),
@NamedQuery(name = "Profil.voirCompte", query = "select cpt from Compte cpt join cpt.profil p where p.compte.codeCompte=:codeCompte"),
@NamedQuery(name = "Profil.findUserProfil", query = "select  pfl FROM Profil pfl  where pfl.compte.role.intitulerole = 'ROLE_USER' ORDER BY pfl.nom  ")
//@NamedQuery(name = "Profil.voirCompte", query = "select cpt from Profil pfl join pfl.compte cpt")
})
public class Profil implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9091482004253569180L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="code_profil")
	private Integer codeProfil;

	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "version", nullable = false)
	@Version
	private Integer version;
	
	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name="activite_ens")
	private String activiteEns;

	@Basic(fetch = FetchType.LAZY)
	@Column(name="adresse")
	private String adresse;

	@Basic(fetch = FetchType.LAZY)
	@Column(name="bureau", unique=true)
	private Integer bureau;

	@Basic(fetch = FetchType.LAZY, optional = false)
	@Lob
	@Column(name="domaine_recherche")
	private String domaineRecherche;

	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name="email", unique=true)
	private String email;

	@Basic(fetch = FetchType.LAZY)
	@Column(name="mobile", unique=true)
	private String mobile;

	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name="nom")
	private String nom;

	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name="prenom")
	private String prenom;

	@Basic(fetch = FetchType.LAZY, optional = false)
	@Lob
	@Column(name="projet_recherche")
	private String projetRecherche;

	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "sexe")
	private String sexe;

	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name = "grade")
	private String grade;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "poste")
	private Integer poste;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name="photo")
	private byte[] photo;
		
	@Basic(fetch = FetchType.LAZY)
	@Column(name="url_dl", unique=true )
	private String urlDl;

	@Basic(fetch = FetchType.LAZY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="d_modification_profil")
	private Date dModificationProfil;
	
	//uni-directional one-to-one association to Compte
	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.REMOVE}, optional = false)
	@JoinColumn(name="compte_id", referencedColumnName = "code_compte", unique=true)
	private Compte compte;


	//bi-directional many-to-one association to Division
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.MERGE}, optional = false)
	@JoinColumn(name="division_id", referencedColumnName = "num_division")
	private Division division;

	
	public Profil() {
	}

	public Integer getCodeProfil() {
		return this.codeProfil;
	}

	public void setCodeProfil(Integer codeProfil) {
		this.codeProfil = codeProfil;
	}
	
	

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getActiviteEns() {
		return this.activiteEns;
	}

	public void setActiviteEns(String activiteEns) {
		this.activiteEns = activiteEns;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	

	

	public Date getDModificationProfil() {
		return this.dModificationProfil;
	}

	public void setDModificationProfil(Date dModificationProfil) {
		this.dModificationProfil = dModificationProfil;
	}

	public String getDomaineRecherche() {
		return this.domaineRecherche;
	}

	public void setDomaineRecherche(String domaineRecherche) {
		this.domaineRecherche = domaineRecherche;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getProjetRecherche() {
		return this.projetRecherche;
	}

	public void setProjetRecherche(String projetRecherche) {
		this.projetRecherche = projetRecherche;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	

	
	public Integer getBureau() {
		return bureau;
	}

	public void setBureau(Integer bureau) {
		this.bureau = bureau;
	}

	public Integer getPoste() {
		return poste;
	}

	public void setPoste(Integer poste) {
		this.poste = poste;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	public String getUrlDl() {
		return this.urlDl;
	}

	public void setUrlDl(String urlDl) {
		this.urlDl = urlDl;
	}
	
	

	public Compte getCompte() {
		return this.compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Date getdModificationProfil() {
		return dModificationProfil;
	}

	public void setdModificationProfil(Date dModificationProfil) {
		this.dModificationProfil = dModificationProfil;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String toString() {
	    return String.format("Profil[%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s]", getCodeProfil(), getNom(), getCompte(), getDModificationProfil(), getDomaineRecherche(), getEmail(), getMobile(), getPrenom(), getProjetRecherche(), getSexe(), getGrade(), getBureau(), getUrlDl());
	  }

}