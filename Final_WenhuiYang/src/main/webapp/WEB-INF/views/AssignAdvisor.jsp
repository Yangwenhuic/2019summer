<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign Advisor</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
<h1 class="p-3 mb-2 bg-dark text-white">please enter the advisor detail</h1>
		<p class="text-warning">${error}</p>
	<form:form action="/myapp/advisor/assign" method="post"
		modelAttribute="newAdvisor" >
		<table class="table table-striped">

			<tr>
				<td>Advisor Name:</td>
				<td><form:input type="text" path="name" required="required" /><form:errors class="text-warning" path="name"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input type="text" path="email" required="required" /><form:errors class="text-warning" path="email"/></td>
			</tr>
			<tr>
				<td>Staff ID:</td>
				<td><form:input type="text" path="staffId" required="required" /><form:errors class="text-warning" path="staffId"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" class = "button" /></td>
			</tr>
		</table>
	</form:form>
	<a class="nav" href="/myapp/course/Admin">Back</a>
</body>
</html>