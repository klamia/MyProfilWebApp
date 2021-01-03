package dz.com.cerist.myprofil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.com.cerist.myprofil.dao.IGradeDao;
import dz.com.cerist.myprofil.entite.Grade;

@Transactional
@Service("iGradeService")
public class GradeServiceImpl implements IGradeService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9038626317754662787L;
	
	@Autowired
	private IGradeDao iGradeDao;
	

	public IGradeDao getiGradeDao() {
		return iGradeDao;
	}

	public void setiGradeDao(IGradeDao iGradeDao) {
		this.iGradeDao = iGradeDao;
	}

	@Override
	public void ajouterGrade(Grade grade) {
		iGradeDao.ajouterGrade(grade);

	}

	@Override
	public Grade getGradebyId(Integer codeGrade) {
		// TODO Auto-generated method stub
		return iGradeDao.getGradebyId(codeGrade);
	}

	@Override
	public Grade getGradebyName(String designation) {
		// TODO Auto-generated method stub
		return iGradeDao.getGradebyName(designation);
	}

	@Override
	public List<Grade> getAllGrade() {
		// TODO Auto-generated method stub
		return iGradeDao.getAllGrade();
	}

	@Override
	public Grade editerGrade(Grade grade) {
		// TODO Auto-generated method stub
		return iGradeDao.editerGrade(grade);
	}

}
