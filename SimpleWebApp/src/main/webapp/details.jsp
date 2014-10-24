<%@page import="com.vamshi.simplewebapp.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
		if(user==null){
			response.sendRedirect("index.jsp");
		}
		else{
			out.println("Your details are :");
			out.println(user.getAge()+", "+user.getGender()+", "+user.getAddress()+", "+
				user.getCountry()+", "+user.getState()+", "+user.getPhone()+", "+user.getFavCoffee()+", "+user.getFavFood());
		}
	%>
</body>
</html>