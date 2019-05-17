<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
<h1 class = "p-3 mb-2 bg-dark text-white">please enter your information</h1>
<form:form action="/myapp/student" method="post"
		modelAttribute="NewStudent">
		<p class="text-warning">${error}</p>
		<table class="table table-striped">


			<tr>
				<td>Student ID Number:</td>
				<td><form:input type="text" path="studentId" required="required" /><form:errors class="text-warning" path="studentId"/></td>
			</tr>
			<tr>
				<td>Student Name:</td>
				<td><form:input type="text" path="studentName" required="required" /><form:errors class="text-warning" path="studentName"/></td>
			</tr>
			<tr>
				<td>Student Major:</td>
				<td><form:input type="text" path="studentInfo" required="required" /><form:errors class="text-warning" path="studentInfo"/></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type ="hidden" name ="type" value= "student"/><input type="submit" value="Submit" class ="button"/></td>
			</tr>
		</table>
	</form:form>
	<a class="nav" href="/myapp">Back</a>
</body>
</html>