package fr.agrorole.dnd.metier;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import fr.agrorole.dnd.dao.UserDAO;
import fr.agrorole.dnd.dto.User;
import fr.agrorole.dnd.outils.JWTParser;

public class UserMetier {

	private UserDAO dao = new UserDAO();
	
	public User getUserFromId(String id) {
		return this.dao.getUser(id);
	}
	
	public String getAuthToken(String id, String pwd) throws Exception {
		User user = this.dao.getUser(id);
		if(null == user) {
			throw new Exception("Nom d'utilisateur incorrect!");
		}
		
		if(user.getPassword() != pwd) {
			throw new Exception("Mot de passe incorrect");
		}		
		
		return JWTParser.buildJWT(id);
	}
	
	public List<User> getUserList(String listParam) {
		if(StringUtils.equals("all", listParam)) {
			return this.dao.listUsers();
		} else {
			return this.dao.listUsersMC(listParam);
		}
	}
	
	public void createUser(User user) throws Exception {
		if(null == user.getUserName()) {
			throw new Exception("Nom d\'utilisateur obligatoire.");
		} else if(!user.getUserName().trim().matches("\\w{3,30}")) {
			throw new Exception("Le nom d\'utilisateur doit comporter entre 3 et 30 caractères, des caractères alphanumériques et des underscores.");
		}
		
		if (null == user.getPassword()) {
			throw new Exception("Le mot de passe est obligatoire");
		} else if (!user.getUserName().trim().matches("\\w{6,30}")) {
			throw new Exception("Le mot de passe doit comporter entre 6 et 30 caractères, des caractères alphanumériques et des underscores.");
		}
		
		if(null == user.getEmail()) {
			throw new Exception("L\'email est obligatoire");
		} else if (!user.getUserName().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			throw new Exception("Ce format d'email est incorrect.");
		}
	}
}
