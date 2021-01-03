package dz.com.cerist.myprofil.security;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * The Class DBPasswordEncrypterBean.
 * @author prabhat.jha
 */

public class DatabasePasswordSecurerBean extends JdbcDaoSupport {
	/** The Constant selectQuery. */
	private String selectQuery = null;
	
	/** The Constant updateQuery. */
	private String updateQuery = null;
	
	/** The password encoder. */
	private PasswordEncoder passwordEncoder = null;

	/**
	 * Encrypt db password.
	 */
	public void encryptDBPassword(){
		getJdbcTemplate().query(getSelectQuery(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				final String username = rs.getString("USERNAME");
				final String encryptedPassword = passwordEncoder.encodePassword(rs.getString("PASSWORD"), null);
				getJdbcTemplate().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con)
							throws SQLException {
						PreparedStatement preparedStatement = con.prepareStatement(getUpdateQuery());
						preparedStatement.setString(1, encryptedPassword);
						preparedStatement.setString(2, username);
						return preparedStatement;
					}
				});
			}
		});
	}
	
	/**
	 * Gets the password encoder.
	 * 
	 * @return the password encoder
	 */
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	/**
	 * Sets the password encoder.
	 * 
	 * @param passwordEncoder
	 *            the new password encoder
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Gets the select query.
	 * 
	 * @return the select query
	 */
	public String getSelectQuery() {
		return selectQuery;
	}

	/**
	 * Sets the select query.
	 * 
	 * @param selectQuery
	 *            the new select query
	 */
	public void setSelectQuery(String selectQuery) {
		this.selectQuery = selectQuery;
	}

	/**
	 * Gets the update query.
	 * 
	 * @return the update query
	 */
	public String getUpdateQuery() {
		return updateQuery;
	}

	/**
	 * Sets the update query.
	 * 
	 * @param updateQuery
	 *            the new update query
	 */
	public void setUpdateQuery(String updateQuery) {
		this.updateQuery = updateQuery;
	}
}
