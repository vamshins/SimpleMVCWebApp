package com.vamshi.simplewebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		// I cannot get the "query" parameter into current UserServlet bcoz it actually belongs to RegisterSerlvet
		System.out.println(getServletConfig().getInitParameter("insertQuery"));
		
		String user = request.getParameter("name");
		
		HttpSession session = request.getSession(true);
		System.out.println("Session id is "+session.getId());
		
		session.setMaxInactiveInterval(15);
		
		ServletContext context = getServletConfig().getServletContext();

		PrintWriter out = response.getWriter();
		
		/*if(session.getId() == cookie sent from 49th user){
			use session object of 49th user;
		} else if(session.getId() == cookie sent from 68th user){
			use session object of 68th user;
		}*/ 
		
		out.println("<html><body>");

		if (user == null || user.equals("")) {
			
			out.println("<h>User from session is "
					+ session.getAttribute("name") + "</h></br>");
			out.println("<h>User from request is "
					+ request.getAttribute("name") + "</h></br>");
			out.println("<h>User from context is "
					+ context.getAttribute("name") + "</h></br>");
			out.println("<h>Session id is "+session.getId()+"</h>");
		} else {
			out.println("<h>User from request is " + user + "</h></br>");
			session.setAttribute("name", user);		//value of user exists upto the time session is valid...session attributes are available only to the user who has
													//initiated that session
			request.setAttribute("name", user);		//value of user exists upto the time response is sent back to server...
			context.setAttribute("name", user); 	//value of user exists permanently unless the web application is stopped or undeployed
													//context attributes are available to all the users irrespective of what request they are sending
		}
		out.println("</body></html>");

		/*
		 * Creates new session every time a new request comes from client
		 * 
		 * HttpSession session = request.getSession();
		 */

		/*
		 * Creates new session when first request comes from client If session
		 * already exists, then it will tell container to use the already
		 * existing one
		 */

		/*
		 * It doesn't create new session at all even a new request comes from
		 * clientrather it tell the container to use the session which has
		 * already been created in theprevious requests.
		 * 
		 * if there is no session in the previous requests, then session
		 * variable is set to nullwhich means no session is created
		 * 
		 * HttpSession session = request.getSession(false);
		 */

		/*
		 * getSesssion(boolean create) if(create equals false){ use already
		 * existing session object, if } else if ( session object doesn't exist
		 * ?){ create new session object and use it. }
		 */
		/*
		 * this is for manual deletion of session object...eg., when a user
		 * clicks logout button.HttpSession session = request.getSession(true);
		 * session.invalidate();
		 */
	}

}
