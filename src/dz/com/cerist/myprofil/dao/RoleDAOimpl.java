package dz.com.cerist.myprofil.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dz.com.cerist.myprofil.entite.Role;

@Repository("iRoleDao")
@Transactional
public class RoleDAOimpl implements IRoleDao, Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6819274723116200692L;
	@PersistenceContext(unitName = "MyProfilePU")
	private EntityManager em;
	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void persist(Role role) {
		em.persist(role);
	}

	@Override
	public Role merge(Role role) {
		
		return em.merge(role);
	}

	@Override
	public Role findroleById(Integer coderole) {
		
		return em.find(Role.class, coderole );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAllroles() {
		
		return em.createNamedQuery("Role.findAll").getResultList();
	}

	@Override
	public Role remove(Role role) {
		em.remove(em.merge(role));
		 return role;
	}

}
