<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
<h1 class="p-3 mb-2 bg-dark text-white">Log in page</h1>
<form:form action="/myapp/login" method="post"
		modelAttribute="account">

		<table class="table table-striped">


			<tr>
				<td>User Name:</td>
				<td><form:input type="text" path="accountName" required="required" /><form:errors class="text-warning" path="accountName"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input type="password" path="password" required="required" /><form:errors class="text-warning" path="password"/></td>
			</tr>
			<tr>

			<tr>
				<td colspan="2"><input type="submit" value="Login" class = "button"/></td>
			</tr>
		</table>
	</form:form>
<a class="nav" href="/myapp">Back</a>
</body>
</html>