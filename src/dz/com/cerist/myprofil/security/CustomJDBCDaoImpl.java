package dz.com.cerist.myprofil.security;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class CustomJDBCDaoImpl extends JdbcDaoImpl {
	public CustomJDBCDaoImpl(){
		super();
	}
}