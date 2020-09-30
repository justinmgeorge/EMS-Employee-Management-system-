package emp.dao;

import emp.connectionClass.mongoConnection;
import emp.model.AdminUser;

public class AdminDAOImpl implements AdminDAO<AdminUser> {
	
	public mongoConnection mc = new mongoConnection();

	@Override
	public boolean SavenewAdminUser(AdminUser user) {		
		
		AdminUser au = new AdminUser().setFirstnamee(user.getFirstnamee()).setLastname(user.getLastname()).setEmail(user.getEmail())
				.setUsername(user.getUsername()).setPassword(user.getPassword()).setUserrole(user.getUserrole());
		
		mc.loadMongoColectionforAdmin().insertOne(au);
		
		return true;
		
	}

	@Override
	public boolean checkAdminUsername(String name) {
		
		return mc.isAdminUsernameTaken(name);

	}

	@Override
	public AdminUser checkLogin(String name) {
		return mc.isUserLoginValid(name);
	}
}
