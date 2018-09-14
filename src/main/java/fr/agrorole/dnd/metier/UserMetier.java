package fr.agrorole.dnd.metier;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import fr.agrorole.dnd.dao.UserDAO;
import fr.agrorole.dnd.dto.User;

public class UserMetier {

	private UserDAO dao = new UserDAO();
	
	public User getUserFromId(String id) {
		return this.dao.getUser(id);
	}
	
	public List<User> getUserList(String listParam) {
		if(StringUtils.equals("all", listParam)) {
			return this.dao.listUsers();
		} else {
			return this.dao.listUsersMC(listParam);
		}
	}
	
	public void createUser(User user) {
		
	}
}
