<%@page import="com.org.dto.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="../components/allcss.jsp"%>


<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
	Student student = (Student) session.getAttribute("studentObj");

	String status = student.getStatus();
	if (status.equalsIgnoreCase("inactive")) {
		response.sendRedirect("../components/blocked.jsp");
	} else if (student == null) {
		response.sendRedirect("student_login.jsp");
	}

	
	else {
	%>
	<%@include file="../components/student_navbar.jsp"%>


	<h3 style="color: red; background-color: yellow; text-align: center">Welcome
		to Student Home page</h3>

	<div id="carouselExampleInterval" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active" data-bs-interval="2000">
				<img src="../img/std.jpg" class="d-block w-100" alt="..."
					height="600px">
			</div>
		</div>
	</div>

	<%
	}
	%>




</body>
</html>