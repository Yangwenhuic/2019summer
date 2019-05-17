<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
<h1 class = "p-3 mb-2 bg-dark text-white">
	Welcome  to the course management app
</h1>

<div class = "p-3 mb-2">
	<a class="nav-link btn btn-sm btn-outline-secondary btn-block" href="/myapp/login" >Login</a>
	<a class="nav-link btn btn-sm btn-outline-secondary btn-block" href="/myapp/student">Register</a>
</div>
<p class="text-warning">${error}</p>
</body>
</html>
