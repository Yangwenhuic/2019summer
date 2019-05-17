<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit my info</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<h1 class = "p-3 mb-2 bg-dark text-white">Edit my information</h1>
<form action="/myapp/student/basicInfo" method= "post" >
<div class="form-group">
<input name = "info" id= "info" type = "text" class = "form-control" required="required" value = "${info}"/>
</div>
<input type= "submit" value = "submit">
</form>
	<a class="nav" href="/myapp/course/Student">Back</a>
</body>
</html>