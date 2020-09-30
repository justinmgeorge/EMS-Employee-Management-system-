package emp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.conversions.Bson;

import emp.connectionClass.mongoConnection;
import emp.model.AdminUser;
import emp.model.Employee;

public class empDAOImpl implements empDAO<Employee> {
	
	mongoConnection mongoColl = new mongoConnection();
		
	private List<Employee> emp = mongoColl.loadMongoColectionforEmployee().find().into(new ArrayList<>());
	
	public List<Employee> getAll(String admin){
		
		List<Employee> empAcessRecord = new ArrayList<>();
		
		Iterator itr = emp.listIterator();
		
		String adminrole = mongoColl.getAdminRole(admin);
		
		if(!adminrole.equals("SystemAdmin")){
			
			while(itr.hasNext()) {				
				Employee e = (Employee) itr.next();
				
				if(e.getRecordOwner().equals(admin)) {	
					
					empAcessRecord.add(e);					
				}				
			}
			
			return empAcessRecord;
		}
		else {
			
			System.out.println("A1 -Get all users method");
			return emp;			
		}		
	}
	
	public Employee getEmp(int id) {
		System.out.println("A2 -Get single user method");
		return mongoColl.getSingleEmp(id);
	}

	
	public void delete(int id) {
			   
	   mongoColl.deleteSingleEmp(id);
		
	}
	
	public boolean checkId(Employee e) {
		
		System.out.println("A3- check if user id exists method");
		boolean isIdSame = mongoColl.isUserIdExists(e.getEmpid());
		return isIdSame;
		
	}
	
	public void save(Employee e) {
		
		System.out.println("A4- save user record method");
		String rn = mongoColl.generateRndm();
		Employee newEmp = new Employee().setId(e.getId()).setName(e.getName()).setSex(e.getSex())
				.setAge(e.getAge()).setEmpid(e.getEmpid()).setBackendId(rn).setRecordOwner(e.getRecordOwner());
	    mongoColl.loadMongoColectionforEmployee().insertOne(newEmp);
	    
	}
	
	public boolean update(Employee e) {
		
		System.out.println("A5- update user record method");
		int id = e.getEmpid();
		String nm = e.getName();
		String sex = e.getSex();
		int age = e.getAge();
		return mongoColl.updateSingleEmp(id, nm, sex, age);		
		
	}	
}









