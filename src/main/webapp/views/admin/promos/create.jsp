<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${promo != null && !promo.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear la promoción.</p>
			</div>
		</c:if>

		<form action="/AgenciaTurismo/views/admin/promos/create.do" method="post">
			<jsp:include page="/views/admin/promos/form.jsp"></jsp:include>
		</form>
	</main>
	<script src="form.js"></script>
</body>
</html>
