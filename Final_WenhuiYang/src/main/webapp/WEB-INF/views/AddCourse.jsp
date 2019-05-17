<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add course</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
<h1 class="p-3 mb-2 bg-dark text-white">please enter the course detail</h1>
		<p class="text-warning">${error}</p>
	<form:form action="/myapp/course/create" method="post"
		modelAttribute="course" >
		<table class="table table-striped">

			<tr>
				<td>Course Name:</td>
				<td><form:input type="text" path="courseName" required="required" /><form:errors class="text-warning" path="courseName"/></td>
			</tr>
			<tr>
				<td>Course CRN:</td>
				<td><form:input type="text" path="crn" required="required" /><form:errors class="text-warning" path="crn"/></td>
			</tr>
			<tr>
				<td>Course Information:</td>
				<td><form:input type="text" path="courseInfo" required="required" /><form:errors class="text-warning" path="courseInfo"/></td>
			</tr>
			<tr>
				<td>Course capacity:</td>
				<td><form:input type="text" path="capacity" required="required" /><form:errors class="text-warning" path="capacity"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Course" class = "button" /></td>
			</tr>
		</table>
	</form:form>
	<a class="nav" href="/myapp/course/Admin">Back</a>
</body>
</html>