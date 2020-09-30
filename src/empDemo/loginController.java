package empDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

import emp.dao.AdminDAO;
import emp.dao.AdminDAOImpl;
import emp.model.AdminUser;

@MultipartConfig
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
	
	AdminDAO<AdminUser> adminUser = null;
	
	public loginController() {
		
		adminUser = new AdminDAOImpl();
		
	}
	
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    	    System.out.println("inside doPost of logincontroller....");
    		String username = request.getParameter("username");
    		String password = request.getParameter("password");    		
    		   		
    		AdminUser user = adminUser.checkLogin(username);
    		if(user == null) {
    			request.setAttribute("ErrorMsg","Error! Check if user exists");  
    			loginfailed(request,response);
    		}
    		else {
			String userpass = user.getPassword();
			System.out.println("password retrived in doPost of logincontroller....");
			System.out.println("value1 :"+userpass);
			System.out.println("value2 :"+password);
			if(userpass.equals(password)) {
				System.out.println("USER VALID....");     		
	      		
				loginSuccess(request,response);
			}
			else {
				
				request.setAttribute("ErrorMsg","Username/Password Invalid !");
				loginfailed(request,response);				
			}
    		}
     }
    	
    	public void loginSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    		
    		HttpSession session = request.getSession();
    		session.setAttribute("username", request.getParameter("username"));
    		response.sendRedirect("empDemo?action=LIST");
    		
    	}
    	
    	public void loginfailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    		dispatcher = request.getRequestDispatcher("index.jsp");
    		dispatcher.forward(request, response);
    		
    	}

}
