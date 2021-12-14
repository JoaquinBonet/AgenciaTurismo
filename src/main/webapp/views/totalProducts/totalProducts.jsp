<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<link rel="stylesheet" href="totalProducts.css">
</head>

<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>
	<br>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
				<c:if test="${errors != null}">
					<ul>
						<c:forEach items="${errors}" var="entry">
							<li><c:out value="${entry.getValue()}"></c:out></li>
						</c:forEach>
					</ul>
				</c:if>
			</p>
		</div>
	</c:if>

	<h1 class="title">PROMOCIONES</h1>
	<div class="card-deck">
		<c:forEach items="${promos}" var="pr">
			<div class="card" style="width: 18em;">
				<img src="${pr.getImg()}" class="card-img-top"
					alt="...">
				<div class="card-body">
					<h5 class="card-title">
						<c:out value="${pr.getNombre()}"></c:out>
					</h5>
					<p class="card-text">
						Costo:
						<c:out value="${pr.getCosto()}"></c:out>
					</p>
					<p class="card-text">
						Duración:
						<c:out value="${pr.getDuracion()}"></c:out>
					</p>
					<p class="card-text">
						Tipo:
						<c:out value="${pr.getTipo()}"></c:out>
					</p>
					<a
						href="../itemDetail/itemDetail.jsp?producto=<c:out value="${pr.nombre}"></c:out>"
						class="btn btn-dark">Ver más</a>
				</div>
			</div>
		</c:forEach>

	</div>
	<br>
	<h1 class="title">ATRACCIONES</h1>
	<div class="card-deck">
		<c:forEach items="${atracciones}" var="at">
			<div class="card" style="width: 18em;">
				<img src="${at.getImg()}" class="card-img-top"
					alt="...">
				<div class="card-body">
					<h5 class="card-title">
						<c:out value="${at.nombre}"></c:out>
					</h5>
					<p class="card-text">
						Costo:
						<c:out value="${at.getCosto()}"></c:out>
					</p>
					<p class="card-text">
						Duración:
						<c:out value="${at.getDuracion()}"></c:out>
					</p>
						<p class="card-text">
						Tipo:
						<c:out value="${at.getTipo()}"></c:out>
					</p>
					<a
						href="../itemDetail/itemDetail.jsp?producto=<c:out value="${at.nombre}"></c:out>"
						class="btn btn-dark">Ver más </a>
				</div>
			</div>
		</c:forEach>

	</div>

	<jsp:include page="/partials/footer.jsp"></jsp:include>
</body>

</html>