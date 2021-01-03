package dz.com.cerist.myprofil.entite;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;



/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Cacheable(true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="role")
@NamedQueries({
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r ")
})
public class Role implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8531050511559731145L;

	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="code_role")
	private Integer coderole;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "version", nullable = false)
	@Version
	private Integer version;
	
	@Basic(fetch = FetchType.EAGER)
	@Column(name="intitule_role")
	private String intitulerole;

	@OneToMany(mappedBy="role", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Compte> comptes;
	
	
	
	
	public Role(){
		
	}

	/**
	 */
	

	public Integer getCoderole() {
		return coderole;
	}


	public void setCoderole(Integer coderole) {
		this.coderole = coderole;
	}



	public String getIntitulerole() {
		return intitulerole;
	}


	public void setIntitulerole(String intitulerole) {
		this.intitulerole = intitulerole;
	}

	

	public Integer getVersion() {
		return version;
	}

	
	public void setVersion(Integer version) {
		this.version = version;
	}
	

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	@Override
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("coderole=[").append(coderole).append("] ");
		buffer.append("intitulerole=[").append(intitulerole).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coderole == null) ? 0 : coderole.hashCode());
		return result;
	}

	/**
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Role))
			return false;
		Role equalCheck = (Role) obj;
		if ((coderole == null && equalCheck.coderole != null) || (coderole != null && equalCheck.coderole == null))
			return false;
		if (coderole != null && !coderole.equals(equalCheck.coderole))
			return false;
		return true;
	}
	

}