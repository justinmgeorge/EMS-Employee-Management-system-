package empDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

import emp.dao.empDAO;
import emp.dao.empDAOImpl;
import emp.model.Employee;

@MultipartConfig
public class empDemo extends HttpServlet {
	
	RequestDispatcher dispatcher = null;
	
	empDAO<Employee> ed = null;
	empDAO<Employee> emp_onSave = null;
	empDAO<Employee> emp_onDel = null;
		
	public empDemo() {
		
		ed = new empDAOImpl();
		
	}
		
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
				
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
		
		case "LIST" : 

		HttpSession session = req.getSession();
		String loggedUser = (String) session.getAttribute("username");
    	System.out.println("LOGGED USER IN EMPDEMO :"+loggedUser);
		
  		MongoClient mongoclient = new MongoClient(new MongoClientURI("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test"));	

  		DB db = mongoclient.getDB("emp");
  		GridFS gfsPhoto = new GridFS(db, "photo");
  		GridFSDBFile imageForOutput2 = gfsPhoto.findOne(loggedUser);
  		if(imageForOutput2 != null) {
  		ByteArrayOutputStream baos = new ByteArrayOutputStream();
  		imageForOutput2.writeTo(baos);
  		byte[] bytes = baos.toByteArray();
  		String base64Image = Base64.getEncoder().encodeToString(bytes);
  		System.out.println("FINAL IMAGE BASE64 VALUE :"+base64Image);
  		req.setAttribute("image",base64Image);
  		session.setAttribute("image", base64Image);
  		session.setAttribute("user", loggedUser);
  		}
		System.out.println("calling LIST method");
		listEmpl(req,resp);
		break;
		
		case "LISTSINGLE" : listSingleEmp(req,resp);
		break;		
		
		case "DELETEEMP" : deleteSingleEmp(req,resp);
		break;
		
		default: listEmpl(req,resp);
		break;		
			
		}
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String loggedUser = (String) session.getAttribute("username");
		req.setAttribute("image", session.getAttribute("image"));
		
		ObjectId id = ObjectId.get();
		String backendId = req.getParameter("backendId");
		System.out.println("VALUE OF backendId :"+backendId);
		String name = req.getParameter("fname");
		int age = Integer.parseInt(req.getParameter("fage"));
		int empid = Integer.parseInt(req.getParameter("fempid"));		
		String gender = req.getParameter("fgender");
		
		Employee ne = new Employee();
		ne.setId(id);
		ne.setEmpid(empid);
		ne.setName(name);
		ne.setSex(gender);
		ne.setAge(age);
		ne.setBackendId(backendId);
		ne.setRecordOwner(loggedUser);
		
		boolean idExist = ed.checkId(ne);
		
		if(!idExist) {
          if(backendId == ""){
            
        	ed.save(ne); 
        	req.setAttribute("Message", "Record Saved.");
          }             
		}
		else {
			if(backendId != "") {
				ed.update(ne); 
	        	req.setAttribute("Message", "Record Updated.");     	
	      		
	      	}
	
			else {
				
			req.setAttribute("Message", "Employee ID already exists.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/employee-add.jsp");
			dispatcher.forward(req,resp);
			
			}
		}	
		
		listEmpl(req,resp);
		
		
	}
	
	public void listEmpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String loggedUser = (String) session.getAttribute("username");
				
		emp_onSave = new empDAOImpl(); 		
		List<Employee> empList = emp_onSave.getAll(loggedUser); 
		System.out.println("B1- listEmpl method");
		request.setAttribute("emplist",empList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/employee-list.jsp");
		dispatcher.forward(request,response);		
	}
	
	public void listSingleEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		int singleEmpId = Integer.parseInt(request.getParameter("empid"));		
		Employee singleEmp = ed.getEmp(singleEmpId);
		request.setAttribute("emplist",singleEmp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/employee-add.jsp");
		dispatcher.forward(request,response);
		System.out.println("B2- listSingleEmp method");
		
	}
	
	public void deleteSingleEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int singleEmpId = Integer.parseInt(request.getParameter("delempid"));
		System.out.println("VALUE OF EMPID :"+singleEmpId);
		request.setAttribute("Message", "Employee record with Id "+singleEmpId+" deleted.");
		ed.delete(singleEmpId);
		listEmpl(request,response);
	}
}
