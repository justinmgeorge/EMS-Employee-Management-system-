package emp.connectionClass;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.*;

import emp.model.AdminUser;
import emp.model.Employee;

public class mongoConnection {

	public MongoCollection<Employee> loadMongoColectionforEmployee() {
			
		ConnectionString constr = new ConnectionString("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");
		CodecRegistry pojocodreg = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codreg = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojocodreg);
		MongoClientSettings clientset = MongoClientSettings.builder()
				                        .applyConnectionString(constr)
				                        .codecRegistry(codreg)
				                        .build();
		
		MongoClient mc = MongoClients.create(clientset);
		MongoDatabase db2 = mc.getDatabase("emp");
		MongoCollection<Employee> empl = db2.getCollection("employeePersonal2",Employee.class);
		
		return empl;

	}
	
	public MongoCollection<AdminUser> loadMongoColectionforAdmin() {
		
		ConnectionString constr = new ConnectionString("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");
		CodecRegistry pojocodreg = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codreg = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojocodreg);
		MongoClientSettings clientset = MongoClientSettings.builder()
				                        .applyConnectionString(constr)
				                        .codecRegistry(codreg)
				                        .build();
		
		MongoClient mc = MongoClients.create(clientset);
		MongoDatabase db2 = mc.getDatabase("emp");
		MongoCollection<AdminUser> newAdmin = db2.getCollection("AdminUsers",AdminUser.class);
		System.out.println("01-MongoConnection created");		
		return newAdmin;
	
	}
	
	public boolean isAdminUsernameTaken(String username) {
				
		MongoClient mongoclient = MongoClients.create("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");		
		MongoDatabase mongodb = mongoclient.getDatabase("emp");
		MongoCollection<Document> coll = mongodb.getCollection("AdminUsers");		
		Document found = (Document) coll.find(new Document("username",username)).first();
		
		if(found !=null) {
			System.out.println("02-Checking admin user existence");
			return true;
		}
		
		return false;
	
	}
	
	public boolean validateAdminLogin(String username, String password) {
		
		ConnectionString constr = new ConnectionString("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");
		CodecRegistry pojocodreg = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codreg = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojocodreg);
		MongoClientSettings clientset = MongoClientSettings.builder()
				                        .applyConnectionString(constr)
				                        .codecRegistry(codreg)
				                        .build();
		
		MongoClient mc = MongoClients.create(clientset);
		MongoDatabase db2 = mc.getDatabase("emp");
		MongoCollection<AdminUser> newAdmin = db2.getCollection("AdminUsers",AdminUser.class);		
		
		System.out.println("01-MongoConnection created");		
		return true;
	
	}	
	
	public String getAdminRole(String username) {
		
		ConnectionString constr = new ConnectionString("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");
		CodecRegistry pojocodreg = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codreg = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojocodreg);
		MongoClientSettings clientset = MongoClientSettings.builder()
				                        .applyConnectionString(constr)
				                        .codecRegistry(codreg)
				                        .build();
		
		MongoClient mc = MongoClients.create(clientset);
		MongoDatabase db2 = mc.getDatabase("emp");
		MongoCollection<AdminUser> admin = db2.getCollection("AdminUsers", AdminUser.class);
		
		AdminUser adminone = admin.find(eq("username", username)).first();
		
		return adminone.getUserrole();
		
		
	}
	
	
	public boolean isUserIdExists(int id) {

		MongoClient mongoclient = MongoClients.create("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");		
		MongoDatabase mongodb = mongoclient.getDatabase("emp");
		MongoCollection<Document> coll = mongodb.getCollection("employeePersonal2");		
		Document found = (Document) coll.find(new Document("empid",id)).first();
		
		if(found !=null) {
			System.out.println("02-Checking user existence");
			return true;
		}
		
		return false;
	
	}
	
	public AdminUser isUserLoginValid(String name) {

		ConnectionString constr = new ConnectionString("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");
		CodecRegistry pojocodreg = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codreg = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojocodreg);
		MongoClientSettings clientset = MongoClientSettings.builder()
				                        .applyConnectionString(constr)
				                        .codecRegistry(codreg)
				                        .build();
		
		MongoClient mc = MongoClients.create(clientset);
		MongoDatabase db2 = mc.getDatabase("emp");
		MongoCollection<AdminUser> adusr = db2.getCollection("AdminUsers", AdminUser.class);
		
		AdminUser user = adusr.find(eq("username",name)).first();
	
		System.out.println("03-Return admin user details");
		return user;
	
	}
	
	public Employee getSingleEmp(int id) {
		
		ConnectionString constr = new ConnectionString("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");
		CodecRegistry pojocodreg = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codreg = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojocodreg);
		MongoClientSettings clientset = MongoClientSettings.builder()
				                        .applyConnectionString(constr)
				                        .codecRegistry(codreg)
				                        .build();
		
		MongoClient mc = MongoClients.create(clientset);
		MongoDatabase db2 = mc.getDatabase("emp");
		MongoCollection<Employee> emps = db2.getCollection("employeePersonal2", Employee.class);
		
		Employee empone = emps.find(eq("empid", id)).first();
	
		System.out.println("03-Get single user details");
		return empone;
		
		}
	
	public void deleteSingleEmp(int id) {
		
		MongoClient mongoclient = MongoClients.create("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");		
		MongoCollection<Document> empl = mongoclient.getDatabase("emp").getCollection("employeePersonal2");		
		empl.deleteOne(Filters.eq("empid",id));	
		
	}
	
	public String generateRndm() {
		
		int min = 1000;  
		int max = 5000;
		int genRnd = (int)(Math.random()*(max-min+1)+min); 
		String randNumbr = "RND"+ Integer.toString(genRnd);
		return randNumbr;	
		
	}
	
	public boolean updateSingleEmp(int id,String newName,String newGender,int newAge) {		

		MongoClient mongoclient = MongoClients.create("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test");
		
		MongoDatabase mongodb = mongoclient.getDatabase("emp");
		MongoCollection coll = mongodb.getCollection("employeePersonal2");
		
		Document found = (Document) coll.find(new Document("empid",id)).first();
		
		if(found !=null) {

			Bson updatedVal = new Document("name",newName).append("age",newAge).append("sex",newGender).append("backendId",generateRndm());
			Bson updateAction = new Document("$set",updatedVal);
			coll.updateOne(found, updateAction);
			System.out.println("04-Update Single user details");		
			return true;
			
		}		
		return false;		
	}
	
}
