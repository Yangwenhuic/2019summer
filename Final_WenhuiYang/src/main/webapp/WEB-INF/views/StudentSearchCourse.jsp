<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Course</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
<p>${error}</p>
<form action="/myapp/course/search" method ="post">
<h1 class = "p-3 mb-2 bg-dark text-white">Search for courses</h1>
<h3 >Search By</h3>
<div class="form-group"> 
<select name ="searchBy" class="form-control">
  <option class="dropdown-item" value="courseName">Course Name</option>
  <option class="dropdown-item" value="crn">Course crn</option>
  <option class="dropdown-item" value="courseInfo">Course Description</option>
</select>
</div>

<div class="form-group">
<input name = "keyWord" required="required" type = "text" placeholder= "key word" class="form-control"/>
</div>
<input type="submit" value = "Submit" class = "btn btn-outline-success my-2 my-sm-0"/>
</form>
</body>
</html>