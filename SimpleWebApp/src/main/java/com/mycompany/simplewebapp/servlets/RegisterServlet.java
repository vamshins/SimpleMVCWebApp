package com.mycompany.simplewebapp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.simplewebapp.database.DataBaseLogic;
import com.mycompany.simplewebapp.pojo.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String insertQuery;
	
    public RegisterServlet() {
        super();        
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertQuery = getServletConfig().getInitParameter("insertQuery");
		System.out.println("insertQuery string from init parameter in web.xml is "+insertQuery);
		
		boolean flag = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String phone = request.getParameter("phone");
		String favFood = request.getParameter("favFood");
		String favCoffee = "";
		String paramValues[] = request.getParameterValues("favCoffee");
		System.out.println(paramValues);
		for(String paramValue : paramValues){
			favCoffee+=paramValue;
		}
		
		DataBaseLogic dataBaseLogic = new DataBaseLogic();
		ServletContext context = getServletConfig().getServletContext();
		Connection connection = dataBaseLogic.getDatabaseConnection(context.getInitParameter("driverName"),
									context.getInitParameter("dbUrl"), context.getInitParameter("dbUsername"), context.getInitParameter("dbPassword"));
		try {
			PreparedStatement pStatement = connection.prepareStatement(insertQuery);
			pStatement.setString(1, username);
			pStatement.setString(2, password);		
			pStatement.setInt(3, Integer.parseInt(age));
			pStatement.setString(4, gender);
			pStatement.setString(5, address);
			pStatement.setString(6, phone);
			pStatement.setString(7, state);
			pStatement.setString(8, country);
			pStatement.setString(9, favCoffee);
			pStatement.setString(10, favFood);
			System.out.println("before execute");
			pStatement.executeQuery();
			System.out.println("after execute");
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(flag){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
			rd.forward(request, response);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
