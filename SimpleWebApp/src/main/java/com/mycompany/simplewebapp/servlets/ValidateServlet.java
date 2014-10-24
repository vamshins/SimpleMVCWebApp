package com.mycompany.simplewebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.simplewebapp.database.DataBaseLogic;
import com.mycompany.simplewebapp.pojo.User;

/**
 * Servlet implementation class ValidateServlet
 */
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
       
    public ValidateServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {    	
    	super.init();    	
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	processRequest(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter(); // gets the "out" stream to write html content to output response
    	System.out.println("Validate Servlet doGet is called");
    	String uname = request.getParameter("username");
    	String pwd = request.getParameter("password");
    	System.out.println("Entered username is "+uname);
    	System.out.println("Entered password is "+pwd);
    	DataBaseLogic dataBaseLogic = new DataBaseLogic();
    	System.out.println("dataBaseLogic "+dataBaseLogic.toString());
    	String validateQuery = "SELECT * FROM USER_NAME WHERE USERNAME=? AND PASSWORD=?";
    	ServletContext context = getServletConfig().getServletContext();
    	Connection connection = dataBaseLogic.getDatabaseConnection(context.getInitParameter("driverName"),
    			context.getInitParameter("dbUrl"), context.getInitParameter("dbUsername"), context.getInitParameter("dbPassword"));
    	boolean flag = false;
    	User user = new User();
    	try {
    		PreparedStatement statement = connection.prepareStatement(validateQuery);
    		statement.setString(1, uname);
    		statement.setString(2, pwd);
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()){
    			flag = true;
    			user.setUsername(rs.getString("USERNAME"));
    			user.setAge(rs.getInt("AGE"));
    			user.setGender(rs.getString("GENDER"));
    			user.setAddress(rs.getString("ADDRESS"));
    			user.setPhone(rs.getString("PHONE"));
    			user.setState(rs.getString("STATE"));
    			user.setCountry(rs.getString("COUNTRY"));
    			user.setFavCoffee(rs.getString("FAVCOFFEE"));
    			user.setFavFood(rs.getString("FAVFOOD"));
    		}
    		
    	} catch (SQLException e) {			
    		e.printStackTrace();
    	}
    	if(flag){						
    		HttpSession session = request.getSession();
    		session.setMaxInactiveInterval(5);
    		session.setAttribute("user", user);
    		RequestDispatcher rd = request.getRequestDispatcher("userpages/userpage.jsp");// ask the request to dispatch the current request to jsp page
    		rd.forward(request, response);
    		//response.sendRedirect("userpages/userpage.jsp");
    	}else {			
    		RequestDispatcher rd = request.getRequestDispatcher("loginfail.jsp");
    		rd.forward(request, response);
    	}		
    	
    }
	
	
    
   	@Override
	public void destroy() {
		super.destroy();
		System.out.println("Servlet destroy() method called...");
	}

	
}
