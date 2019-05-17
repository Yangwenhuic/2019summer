<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student list</title>
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
<h1 class = "p-3 mb-2 bg-dark text-white">Student List</h1>
<form action="/myapp/advisor" method="post">
		<table class="table table-striped">
<c:forEach var="student" items="${studentList}">
			<tr>
				<td>Student Name: <c:out value="${student.studentName}"></c:out></td>
				<td>Student Information:<c:out value="${student.studentInfo}"></c:out></td>
				<td>Student ID: ${student.studentId}<input onchange ="goFurther()" name = "students" type="checkbox" class = "check" value = "${student.studentId}"/></td>
			</tr>
</c:forEach>


			<tr>
				<td colspan="2"><input type="submit" disabled value="Assign To Advisor" id = "del_event" class="button"/></td>
			</tr>
		</table>
	</form>
<a class="nav" href="/myapp/course/Admin">Back</a>
</body>
</html>