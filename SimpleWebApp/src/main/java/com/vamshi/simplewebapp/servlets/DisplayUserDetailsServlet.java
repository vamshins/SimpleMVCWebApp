package com.vamshi.simplewebapp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayUserDetailsServlet
 */
public class DisplayUserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	

    public DisplayUserDetailsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user");
		System.out.println("User is "+user);		
	}

}
