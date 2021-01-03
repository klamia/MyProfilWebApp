package dz.com.cerist.myprofil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.com.cerist.myprofil.dao.IDivisionDao;
import dz.com.cerist.myprofil.entite.Division;

@Transactional
@Service("iDivisionService")
public class DivisionServiceImpl implements IDivisionService, Serializable {

	private static final long serialVersionUID = 9181082844027438745L;

	
	@Autowired
	private IDivisionDao iDivisionDao;
	
	
	public IDivisionDao getiDivisionDao() {
		return iDivisionDao;
	}

	public void setiDivisionDao(IDivisionDao iDivisionDao) {
		this.iDivisionDao = iDivisionDao;
	}

	@Override
	public void ajouterDivision(Division division) {
		// TODO Auto-generated method stub
		iDivisionDao.ajouterDivision(division);
	}

	@Override
	public Division getDivisionbyId(Integer numDivision) {
		// TODO Auto-generated method stub
		return iDivisionDao.getDivisionbyId(numDivision);
	}

	@Override
	public List<Division> getAllDivision() {
		// TODO Auto-generated method stub
		return iDivisionDao.getAllDivision();
	}

	@Override
	public Division editerDivision(Division division) {
		// TODO Auto-generated method stub
		return iDivisionDao.editerDivision(division);
	}

}
