package dz.com.cerist.myprofil.entite;


import java.io.Serializable;

import java.util.Date;


import javax.persistence.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;







/**
 * The persistent class for the compte database table.
 * 
 */
@Entity
@Cacheable(true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="compte")
@NamedQueries({
@NamedQuery(name = "Compte.findAll", query = "SELECT cpt FROM Compte cpt "),
@NamedQuery(name = "Compte.voirProfil", query = "SELECT p from Profil p JOIN p.compte cpt WHERE cpt.codeCompte=:idCompte"),
@NamedQuery(name = "Compte.findByLogin", query = "SELECT cpt FROM Compte cpt WHERE cpt.login = :login")
//@NamedQuery(name = "Compte.voirProfil", query = "select p from Compte cpt join cpt.profil p")
//@NamedQuery(name = "Compte.disableCompte", query = "select cpt from Profil p where p.compte=:compte")

})

public class Compte implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572852755454351294L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="code_compte")
	private Integer codeCompte;

	
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="d_creation_compte", insertable = false, updatable = false)
	private Date dCreationCompte;
 
	@Basic(optional = false)
	@Column(name = "version")
	@Version
	private Integer version;
	
	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name="login", unique = true)
	private String login;

	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name="pwd")
	private String password;
	
	@Email
	@Basic(fetch = FetchType.EAGER, optional = false)
    @Column(unique = true)
    private String emailProf;
	
	@Basic(optional = false)
	@Column(name="isAdmin")
	private Boolean isAdmin=false;
	
	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name="enabled")
	private Boolean enabled=true;

	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column(name="has_profil")
	private Boolean hasProfil=false;
	
	private String actionToken;
	
	@OneToOne(mappedBy = "compte", fetch = FetchType.LAZY)
	private Profil profil;
	

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="roleId", updatable=false, referencedColumnName="code_role")
	private Role role;
	
	
	
	
	public Compte() {
	}
	
		
	public Compte(String login, String password) {
		
		
		setLogin(login);
		setPassword(password);
		
	}

	public Integer getCodeCompte() {
		return this.codeCompte;
	}

	public void setCodeCompte(Integer codeCompte) {
		this.codeCompte = codeCompte;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public String getEmailProf() {
		return emailProf;
	}


	public void setEmailProf(String emailProf) {
		this.emailProf = emailProf;
	}

     
	public Boolean getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getActionToken() {
		return actionToken;
	}


	public void setActionToken(String actionToken) {
		this.actionToken = actionToken;
	}


	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getHasProfil() {
		return this.hasProfil;
	}

	public void setHasProfil(Boolean hasProfil) {
		this.hasProfil = hasProfil;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	
	public Date getdCreationCompte() {
		return dCreationCompte;
	}

	public void setdCreationCompte(Date dCreationCompte) {
		this.dCreationCompte = dCreationCompte;
	}
	
	

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	/*
	public void addRole(Role role) {
		roles.add(role);
		}
	
	public void removeRole(Role role) {
		roles.remove(role);
		}
	
	*/
	public void addProfil(Profil prf) {
        prf.setCompte(this);
        this.getProfil().getCompte().addProfil(prf);

    }
	
	
	public String toString() {
	    return String.format("Compte[%s,%s,%s,%s,%s]", getCodeCompte(), getVersion(), getLogin(), getPassword(), getIsAdmin());
	  }
	
     
}