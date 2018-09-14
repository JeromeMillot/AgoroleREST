package interfaces;

import java.util.List;

import dto.User;

public interface IUserDAO {
	public void addUser(User user);
	public List<User> listUsers();
	public List<User> listUsersMC(String mc);
	public User getUser(String userName);
	public void updateUser(User user);
	public void deleteUser(String userName);
}
