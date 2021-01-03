package dz.com.cerist.myprofil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import dz.com.cerist.myprofil.dao.IRoleDao;
import dz.com.cerist.myprofil.entite.Role;

@Transactional
@Service("iRoleService")
public class RoleServiceImpl implements IRoleService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5608322016943944640L;
	@Autowired
	private IRoleDao iroledao;
	
	
	public IRoleDao getIroledao() {
		return iroledao;
	}

	public void setIroledao(IRoleDao iroledao) {
		this.iroledao = iroledao;
	}

	@Override
	public void persist(Role role) {
		iroledao.persist(role);

	}

	@Override
	public Role merge(Role role) {
		
		return iroledao.merge(role);
	}

	@Override
	public Role findroleById(Integer coderole) {
		
		return iroledao.findroleById(coderole);
	}

	@Override
	public List<Role> findAllroles() {
		// TODO Auto-generated method stub
		return iroledao.findAllroles();
	}

	@Override
	public Role remove(Role role) {
		// TODO Auto-generated method stub
		return iroledao.remove(role);
	}

}
