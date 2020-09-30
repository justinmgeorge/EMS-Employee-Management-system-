package emp.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class AdminUser {
	
	@BsonProperty("id")
	private ObjectId id;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	private String userrole;	
	

	public String getUserrole() {
		return userrole;
	}
	public AdminUser setUserrole(String userrole) {
		this.userrole = userrole;
		return this;
	}
	public ObjectId getId() {
		return id;
	}
	public AdminUser setId(ObjectId id) {
		this.id = id;
		return this;
	}
	public String getFirstnamee() {
		return firstname;
	}
	public AdminUser setFirstnamee(String firstnamee) {
		this.firstname = firstnamee;
		return this;
	}
	public String getLastname() {
		return lastname;
	}
	public AdminUser setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public AdminUser setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public AdminUser setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public AdminUser setPassword(String password) {
		this.password = password;
		return this;
	}
	
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", userrole=" + userrole + "]";
	}

}
