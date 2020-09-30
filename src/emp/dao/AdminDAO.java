package emp.dao;

import emp.model.AdminUser;

public interface AdminDAO<AdminUser> {
	
	public boolean SavenewAdminUser(AdminUser user);
	
	public boolean checkAdminUsername(String name);
	
	public AdminUser checkLogin(String name);

}
