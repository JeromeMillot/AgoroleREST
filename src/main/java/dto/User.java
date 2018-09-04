package dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name="User.findAll",
                query="SELECT u FROM User u"),
    @NamedQuery(name="User.findByName",
                query="SELECT u FROM User u WHERE u.userName = :userName")
}) 
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String userName;
	private String password;
	private String email;
	private String role;
	private Timestamp datecrea;
	private String firstName;
	private String lastName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getDatecrea() {
		return datecrea;
	}
	public void setDatecrea(Timestamp datecrea) {
		this.datecrea = datecrea;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

}
