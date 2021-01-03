package dz.com.cerist.myprofil.entite;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

/**
 * The persistent class for the division database table.
 * 
 */
@Entity
@Cacheable(true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "division")
@NamedQueries({ @NamedQuery(name = "Division.findAll", query = "SELECT dv FROM Division dv ") })
public class Division implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "num_division")
	private Integer numDivision;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "version")
	@Version
	private Integer version;

	@Basic(fetch = FetchType.EAGER)
	@Column(name = "code_division")
	private String codeDivision;

	@Basic(fetch = FetchType.EAGER)
	@Column(name = "intitule_division")
	private String intituleDivision;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "url_division")
	private String urlDivision;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "tel_division")
	private String telDivision;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "fax_division")
	private String faxDivision;
	
	
    @OneToMany(mappedBy = "division", fetch = FetchType.LAZY )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Profil> profils;

	public Division() {
	}

	public Integer getNumDivision() {
		return this.numDivision;
	}

	public void setNumDivision(Integer numDivision) {
		this.numDivision = numDivision;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCodeDivision() {
		return this.codeDivision;
	}

	public void setCodeDivision(String codeDivision) {
		this.codeDivision = codeDivision;
	}

	public String getIntituleDivision() {
		return this.intituleDivision;
	}

	public void setIntituleDivision(String intituleDivision) {
		this.intituleDivision = intituleDivision;
	}

	
	
	
	public String getUrlDivision() {
		return urlDivision;
	}

	public void setUrlDivision(String urlDivision) {
		this.urlDivision = urlDivision;
	}

	public String getTelDivision() {
		return telDivision;
	}

	public void setTelDivision(String telDivision) {
		this.telDivision = telDivision;
	}

	public String getFaxDivision() {
		return faxDivision;
	}

	public void setFaxDivision(String faxDivision) {
		this.faxDivision = faxDivision;
	}

	public List<Profil> getProfils() {
		return this.profils;
	}

	public void setProfils(List<Profil> profils) {
		this.profils = profils;
	}

	public Profil addProfil(Profil profil) {
		getProfils().add(profil);
		profil.setDivision(this);

		return profil;
	}

	public Profil removeProfil(Profil profil) {
		getProfils().remove(profil);
		profil.setDivision(null);

		return profil;
	}

}