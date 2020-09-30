package emp.dao;

import java.util.List;

import emp.model.AdminUser;
import emp.model.Employee;

public interface empDAO<Employee> {
	
	List<Employee> getAll(String user);
	
	Employee getEmp(int id);
	
	public boolean update(Employee emp);
	
	public boolean checkId(Employee e);
	
	void delete(int id);
		
	public void save(Employee e);

}
