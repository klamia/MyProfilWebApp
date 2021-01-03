package dz.com.cerist.myprofil.service;

import java.util.List;

import dz.com.cerist.myprofil.entite.Role;

public interface IRoleService {

	public void persist(Role role);

	public Role merge(Role role);

	public Role findroleById(Integer coderole);
		
	public List<Role> findAllroles () ;
	
	public Role remove(Role role);
}
