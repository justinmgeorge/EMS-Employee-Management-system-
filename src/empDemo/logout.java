package empDemo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	System.out.println("INSIDE LOGOUT GET METHOD");
    	String res = req.getParameter("result");
    	if(res.equals("LOGOUT")) {
    		System.out.println("INSIDE LOGOUT CALLING doPost");
    		doPost(req,resp);
    	}
    	
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		System.out.println("INSIDE LOGOUT - USERNAME :"+user);
		
		String message = "You have been logged out "+ user +"!";
		request.setAttribute("Userrecord",message);
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
		disp.forward(request, response);
		
		session.removeAttribute("username");		

	}

}
