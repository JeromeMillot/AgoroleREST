package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang.StringUtils;

import dto.User;

public class UserDAO extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7862124686542436938L;

	protected User getUserDAO(String id, String mdp) throws SQLException
	{
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner(DB2DataSourceFactory.getDataSource());

		// Vérification de l'existence de COFOSEM dans TAREFOU, erreur si présent
		String requete = 
				"SELECT"
				+ " *"
				+ " FROM"
				+ " USERS U"
				+ " LEFT OUTER JOIN OSPHF.TAUTILI V ON ( V.COPAIP = U.COPAIP AND V.NUNATI = U.NUNATI )"				
				+ " WHERE U.ID = ? AND U.MDP = ?";
		
		// definition of the resultset mapping 
		ResultSetHandler<User> userResultsetHandler = new BeanHandler<User>(User.class) {

			@Override
			public User handle(ResultSet rs) throws SQLException {

				User user = new User();
				while(rs.next())
				{
					
					user.setUserName(StringUtils.trimToNull(rs.getString("ID")));
					user.setEmail(StringUtils.trimToNull(rs.getString("EMAIL")));
					user.setFirstName(StringUtils.trimToNull(rs.getString("FIRSTN")));
					user.setLastName(StringUtils.trimToNull(rs.getString("LASTN")));
					user.setRole(StringUtils.trimToNull(rs.getString("ROLE")));
					user.setDatecrea(rs.getTimestamp("DATECREA"));
					
				}
				return user;
			}
		};
		
		return run.query(requete, userResultsetHandler,
				id, mdp);
	}
}
