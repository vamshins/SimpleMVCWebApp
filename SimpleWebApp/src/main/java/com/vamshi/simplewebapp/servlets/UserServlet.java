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

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println(getServletConfig().getInitParameter("insertQuery"));

		String user = request.getParameter("name");

		HttpSession session = request.getSession(true);
		System.out.println("Session id is " + session.getId());

		session.setMaxInactiveInterval(15);

		ServletContext context = getServletConfig().getServletContext();

		PrintWriter out = response.getWriter();

		out.println("<html><body>");

		if (user == null || user.equals("")) {

			out.println("<h>User from session is " + session.getAttribute("name") + "</h></br>");
			out.println("<h>User from request is " + request.getAttribute("name") + "</h></br>");
			out.println("<h>User from context is " + context.getAttribute("name") + "</h></br>");
			out.println("<h>Session id is " + session.getId() + "</h>");
		} else {
			out.println("<h>User from request is " + user + "</h></br>");
			session.setAttribute("name", user); // value of user exists upto the
												// time session is
												// valid...session attributes
												// are available only to the
												// user who has
												// initiated that session
			request.setAttribute("name", user); // value of user exists upto the
												// time response is sent back to
												// server...
			context.setAttribute("name", user); // value of user exists
												// permanently unless the web
												// application is stopped or
												// undeployed
												// context attributes are
												// available to all the users
												// irrespective of what request
												// they are sending
		}
		out.println("</body></html>");

	}

}
