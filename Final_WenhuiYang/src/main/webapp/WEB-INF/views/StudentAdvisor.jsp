<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Advisor</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<h1 class ="p-3 mb-2 bg-dark text-white">Advisor Information </h1>
<p class="text-warning">${error}</p>
		<table class="table table-striped">

			<tr>
				<td>Advisor Name:</td>
				<td>${myAdvisor.name}</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>${myAdvisor.email}</td>
			</tr>
		</table>
		<a class="nav" href="/myapp/course/Student">Back</a>
</body>
</html>