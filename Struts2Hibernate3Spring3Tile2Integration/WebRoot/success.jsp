<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Struts2-Spring-Tiles integration | dineshonjava.com</title>
</head>
<body>

	<h2>List of User</h2>
   	<ul>
	  <li>User Name : <s:property value="userName" /></li>
	  <li>User Age : <s:property value="userAge" /></li>
	  <li>User Gender : <s:property value="userGender" /></li>
	  <li>User Jobs : <s:property value="userJob" /></li>
	  <li>User Hobbies : <s:property value="userHobbies" /></li>
 </ul>	
</body>
</html>