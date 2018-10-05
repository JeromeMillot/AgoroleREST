package fr.agrorole.dnd.metier;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.auth0.jwt.exceptions.JWTCreationException;

import fr.agrorole.dnd.dao.UserDAOImpl;
import fr.agrorole.dnd.dto.User;
import fr.agrorole.dnd.exceptions.UserFieldsException;
import fr.agrorole.dnd.outils.JWTParser;

public class UserMetier {

	private UserDAOImpl dao = new UserDAOImpl();
	
	public User getUserFromId(String id) {
		return this.dao.getUser(id);
	}
	
	public String getAuthToken(String id, String pwd) throws UserFieldsException, IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		User user = this.dao.getUser(id);
		if(null == user) {
			throw new UserFieldsException("Nom d'utilisateur incorrect!");
		}
		
		if(user.getPassword() != pwd) {
			throw new UserFieldsException("Mot de passe incorrect");
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
	
	public User createUser(User user) throws UserFieldsException {
		if(null == user.getUserName()) {
			throw new UserFieldsException("Nom d\'utilisateur obligatoire.");
		} else if(!user.getUserName().trim().matches("\\w{3,30}")) {
			throw new UserFieldsException("Le nom d\'utilisateur doit comporter entre 3 et 30 caractères, des caractères alphanumériques et des underscores.");
		}
		
		if(null == user.getPassword()) {
			throw new UserFieldsException("Le mot de passe est obligatoire");
		} else if (!user.getUserName().trim().matches("\\w{6,30}")) {
			throw new UserFieldsException("Le mot de passe doit comporter entre 6 et 30 caractères, des caractères alphanumériques et des underscores.");
		}
		
		if(null == user.getEmail()) {
			throw new UserFieldsException("L\'email est obligatoire");
		} else if (!user.getUserName().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			throw new UserFieldsException("Ce format d'email est incorrect.");
		}
		
		if(null == user.getRole()) {
			throw new UserFieldsException("Le role est manquant.");
		}		
		
		this.dao.addUser(user);
		
		return this.getUserFromId(user.getUserName());
	}
	
	public User updateUser(User user) throws UserFieldsException {
		if(null == user.getUserName()) {
			throw new UserFieldsException("Nom d\'utilisateur obligatoire.");
		} else if(!user.getUserName().trim().matches("\\w{3,30}")) {
			throw new UserFieldsException("Le nom d\'utilisateur doit comporter entre 3 et 30 caractères, des caractères alphanumériques et des underscores.");
		}
		
		if(null == user.getPassword()) {
			throw new UserFieldsException("Le mot de passe est obligatoire");
		} else if (!user.getUserName().trim().matches("\\w{6,30}")) {
			throw new UserFieldsException("Le mot de passe doit comporter entre 6 et 30 caractères, des caractères alphanumériques et des underscores.");
		}
		
		if(null == user.getEmail()) {
			throw new UserFieldsException("L\'email est obligatoire");
		} else if (!user.getUserName().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			throw new UserFieldsException("Ce format d'email est incorrect.");
		}
		
		if(null == user.getRole()) {
			throw new UserFieldsException("Le role est manquant.");
		}
		
		this.dao.updateUser(user);
		
		return this.getUserFromId(user.getUserName());
	}
	
	public String deleteUser(String id) {
		this.dao.deleteUser(id);		
		return id;
	}
}
