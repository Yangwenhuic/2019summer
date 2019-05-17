<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<title>Welcome Administrator!</title>

</head>
<body>
<h1 class="p-3 mb-2 bg-dark text-white">Welcome Administrator!</h1>


<ul class="nav nav-tabs" style="height: 100%">
    <li class="nav-item">
        <a class="nav-link" href="/myapp/course/">View Courses</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/myapp/course/create">Add new Course</a>
    </li>
        <li class="nav-item">
        <a class="nav-link" href="/myapp/advisor">Assign Students to Advisor</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/myapp">Log out</a>
    </li>
</ul>
</body>
</html>