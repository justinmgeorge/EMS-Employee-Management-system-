package emp.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Employee {
	
	@BsonProperty("id")
	private ObjectId id;
	private String backendId;
	private int empid;
	private String name;
	private String sex;
	private int age;
	private String recordOwner;	
	

	public String getRecordOwner() {
		return recordOwner;
	}
	public Employee setRecordOwner(String recordOwner) {
		this.recordOwner = recordOwner;
		return this;
	}
	public String getBackendId() {
		return backendId;
	}
	public Employee setBackendId(String backendId) {
		this.backendId = backendId;
		return this;
	}
	public int getEmpid() {
		return empid;
	}
	public Employee setEmpid(int empid) {
		this.empid = empid;
	    return this;
	}
	public ObjectId getId() {
		return id;
	}
	public Employee setId(ObjectId id2) {
		this.id = id2;
		return this;
	}
	public String getName() {
		return name;
	}
	public Employee setName(String name) {
		this.name = name;
		return this;
	}
	public String getSex() {
		return sex;
	}
	public Employee setSex(String sex) {
		this.sex = sex;
		return this;
	}
	public int getAge() {
		return age;
	}
	public Employee setAge(int age) {
		this.age = age;
		return this;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", backendId=" + backendId + ", empid=" + empid + ", name=" + name + ", sex="
				+ sex + ", age=" + age + ", recordOwner=" + recordOwner + "]";
	}	

}
