<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Course</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script type="text/javascript">
function goFurther(){
	
	var elements = document.getElementsByClassName("check");
	var button = false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked==true){
			button = true;
			}
		}
	if(button == true){
		document.getElementById("del_event").disabled = false;
		}else{
			document.getElementById("del_event").disabled = true;
			}


	}
</script>
</head>
<body>
<h1 class = "p-3 mb-2 bg-dark text-white">Register Courses</h1>
<form action="/myapp/course/register" method="post">
		<table class="table table-striped">
<c:forEach var="course" items="${courseList}">
<c:set var = "disable" value =""/>
<c:if test="${!course.registerable}">
<c:set var = "disable" value ="disabled"/>
</c:if>
<%-- <c:out value="${course.registerable }"></c:out> --%>
			<tr>
				<td>Course Name: <c:out value="${course.courseName}"></c:out></td>
				<td>Course Information:<c:out value="${course.courseInfo}"></c:out></td>
				<td>Course Capacity:<c:out value="${course.capacity}"></c:out></td>
				<td>Course crn: ${course.crn}<input name = "courses" ${disable} onchange ="goFurther()" class ="check" type="checkbox" value = "${course.crn}"/></td>
			</tr>
</c:forEach>



			<tr>
				<td colspan="2"><input type="submit" id = "del_event" disabled value="Register" class ="button"/></td>
			</tr>
		</table>
	</form>
<a class="nav" href="Student">Back</a>
</body>
</html>