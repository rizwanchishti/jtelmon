<%@ page language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<% session.invalidate(); %>
You are now logged out!!

<a href="${pageContext.request.contextPath}/login">go back</a>