<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/partials/navAdmin.jsp"></jsp:include>

	<main class="container">

		<form action="/AgenciaTurismo/views/admin/users/create.do" method="post">
			<jsp:include page="/views/admin/users/form.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>