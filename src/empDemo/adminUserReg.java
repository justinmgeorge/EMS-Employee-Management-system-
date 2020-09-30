package empDemo;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

import emp.dao.AdminDAO;
import emp.dao.AdminDAOImpl;
import emp.model.AdminUser;

@MultipartConfig
public class adminUserReg extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	
	private static final long serialVersionUID = 1L;
	
	
	AdminDAO<AdminUser> admindao = null;
	
	public adminUserReg() {
		
		admindao = new AdminDAOImpl();
	}
	
	RequestDispatcher rd = null;
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Inside adminUserReg servlet...");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String mailid = request.getParameter("mailid");
		String username = request.getParameter("usrnm");
		String password = request.getParameter("pass");
		String userrole = request.getParameter("role");
		
		System.out.println("value of username inside doPost :"+username);
		System.out.println("value of password inside doPost :"+password);
		System.out.println("value of user role inside doPost :"+userrole);
		
		AdminUser user = new AdminUser();
		user.setFirstnamee(fname);
		user.setLastname(lname);
		user.setEmail(mailid);
		user.setUsername(username);
		user.setPassword(password);	
		user.setUserrole(userrole);
		
		
		if(!admindao.checkAdminUsername(username)) {
			
			Part filepart = request.getPart("photo");
			
			InputStream inputstream = null;
			if(filepart != null) {
				
				System.out.println("INSIDE NO IMAGE IF BLOCK");
				long filesize = filepart.getSize();
				if(filesize == 0){

					request.setAttribute("Userrecord","*Profile Image is Mandatory.");
					noImageError(request,response);

				}
				else {
				String filecontent = filepart.getContentType();
				inputstream = filepart.getInputStream();
				}				
			}
			
			
			
			MongoClient mongoclient = new MongoClient(new MongoClientURI("mongodb+srv://justin07:justin07@joescluster.34m5d.mongodb.net/test"));	
			
			//saving a file	
			DB db = mongoclient.getDB("emp");
			GridFS gridFS = new GridFS(db,"photo");
			GridFSInputFile gridFSInputFile = gridFS.createFile(inputstream);
			gridFSInputFile.setFilename(username);
			gridFSInputFile.save();
			
			System.out.println("User registered...");
			request.setAttribute("Userrecord", "User registered successfully!!");
			admindao.SavenewAdminUser(user);
			saveAdmin(request,response);
			
		}
		else {
			
			System.out.println("User not registered...");
			request.setAttribute("Userrecord", "Username already exists !!");
			ErrorAdmin(request,response);
		}		
			
		}
	
	
	    public void saveAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
	    		    	
	    	rd = req.getRequestDispatcher("index.jsp");
	    	rd.forward(req,resp);	
	    
	    }
	    
	    public void ErrorAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
			
			rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req,resp);	
		    
		}	  
	    
	    public void noImageError(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
	    	
	    	rd = req.getRequestDispatcher("/views/admin-register.jsp");
	    	rd.forward(req, resp);
	    	
	    }
	    
}
