package dz.com.cerist.myprofil.entite;

import java.io.Serializable;


import javax.persistence.*;

import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * The persistent class for the grade database table.
 * 
 */
@Entity
@Cacheable(true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="grade")
@NamedQueries({
@NamedQuery(name = "Grade.findAll", query = "SELECT gd FROM Grade gd ")
})
public class Grade implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7425099745934674683L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="code_grade")
	private Integer codeGrade;
    
	@Basic(fetch = FetchType.LAZY, optional = false)
	@Column(name = "version")
	@Version
	private Integer version;
	
	
	@Basic(fetch = FetchType.EAGER, optional = false)
	@Column( name="designation")
	private String designation;

	
	
	public Grade() {
	}

	public Integer getCodeGrade() {
		return this.codeGrade;
	}

	public void setCodeGrade(Integer codeGrade) {
		this.codeGrade = codeGrade;
	}

	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
	
	@Override
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("codeGrade=[").append(codeGrade).append("] ");
		buffer.append("designation=[").append(designation).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeGrade == null) ? 0 : codeGrade.hashCode());
		return result;
	}

	/**
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Grade))
			return false;
		Grade equalCheck = (Grade) obj;
		if ((codeGrade == null && equalCheck.codeGrade != null) || (codeGrade != null && equalCheck.codeGrade== null))
			return false;
		if (codeGrade != null && !codeGrade.equals(equalCheck.codeGrade))
			return false;
		return true;
	}

}