<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<link rel="stylesheet" href="admin.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="/partials/navAdmin.jsp"></jsp:include>
	<hr>
	<br>
	<main>
		<section id="usuarios">
			<div class="container">
				<h1 id="title">USUARIOS</h1>
				<br>
				<table class="table table-striped my_table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Tiempo</th>
							<th scope="col">Presupuesto</th>
							<th scope="col">Preferencia</th>
							
							<th scope="col">Acciones
								<a
								href="/AgenciaTurismo/views/admin/users/create.do"
								class="btn btn-success" role="button">Crear usuario</a>
							</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<c:if test="${!user.getAdmin()}">
								<tr>
									<th scope="row">-</th>
									<td><c:out value="${user.getNombre()}"></c:out></td>
									<td><c:out value="${user.getTiempo()}"></c:out></td>
									<td><c:out value="${user.getPresupuesto()}"></c:out></td>
									<td><c:out value="${user.getTipo()}"></c:out></td>

									<td><a
										href="/AgenciaTurismo/views/admin/users/delete.do?name=${user.getNombre()}"
										class="btn btn-danger rounded" role="button">Baja</a> <a
										href="/AgenciaTurismo/views/admin/users/edit.do?name=${user.getNombre()}"
										class="btn btn-primary rounded" role="button">Modificar</a></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<br>
			</div>
		</section>
		<hr>
		<section id="atracciones">
			<div class="container">
				<br>
				<h1 id="title">ATRACCIONES</h1>
				<br>
				<table class="table table-striped my_table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Tipo</th>
							<th scope="col">Duración</th>
							<th scope="col">Costo</th>
							<th scope="col">Cupo</th>
							<th scope="col">Acciones <a
								href="/AgenciaTurismo/views/admin/attractions/create.do"
								class="btn btn-success" role="button">Crear atracción</a>
							</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${attractions}" var="at">
							<tr>
								<th scope="row">-</th>
								<td><c:out value="${at.getNombre()}"></c:out></td>
								<td><c:out value="${at.getTipo()}"></c:out></td>
								<td><c:out value="${at.getDuracion()}"></c:out></td>
								<td><c:out value="${at.getCosto()}"></c:out></td>
								<td><c:out value="${at.getCupo()}"></c:out></td>
								<td><a
									href="/AgenciaTurismo/views/admin/attractions/delete.do?name=${at.getNombre()}"
									class="btn btn-danger rounded" role="button">Baja</a> <a
									href="/AgenciaTurismo/views/admin/attractions/edit.do?name=${at.getNombre()}"
									class="btn btn-primary rounded" role="button">Modificar</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<br>
			</div>

		</section>
		<hr>
		<section id="tipos">
			<div class="container">
				<br>
				<h1 id="title">TIPOS DE ATRACCIONES</h1>
				<br>
				<table class="table table-striped my_table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Tipo</th>
							<th scope="col">Acciones<a
								href="/AgenciaTurismo/views/admin/tipo/create.do"
								class="btn btn-success" role="button">Crear tipo de
									atracción</a>

							</th>

						</tr>
					</thead>
					<tbody>

						<c:forEach items="${tipos}" var="tipo">
							<tr>
								<th scope="row">-</th>
								<td><c:out value="${tipo}"></c:out></td>

								<td><a
									href="/AgenciaTurismo/views/admin/tipo/delete.do?name=${tipo}"
									class="btn btn-danger rounded" role="button">Baja</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<br>
			</div>
		</section>
		<hr>
		<section id="promociones">
			<div class="container">
				<br>
				<h1 id="title">PROMOCIONES</h1>
				<br>
				<table class="table table-striped my_table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre</th>
							<th scope="col">Tipo promoción</th>
							<th scope="col">Descuento</th>
							<th scope="col">Costo</th>
							<th scope="col">Atracciones incluidas</th>
							<th scope="col">Acciones
								<a
									href="/AgenciaTurismo/views/admin/promos/create.do"
									class="btn btn-success rounded" role="button">Crear promoción</a>
							</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${promos}" var="promo">
							<tr>
								<th scope="row">-</th>
								<td><c:out value="${promo.getNombre()}"></c:out></td>
								<td><c:out value="${promo.getClass().getSimpleName()}"></c:out></td>
								<td><c:out value="${promo.getPorcentaje()}"></c:out></td>
								<td><c:out value="${promo.getCosto()}"></c:out></td>
								<td><c:out value="${promo.getAtracciones()}"></c:out></td>
								<td><a
									href="/AgenciaTurismo/views/admin/promos/delete.do?name=${promo.getNombre()}"
									class="btn btn-danger rounded" role="button">Baja</a> <a
									href="/AgenciaTurismo/views/admin/promos/edit.do?name=${promo.getNombre()}"
									class="btn btn-primary rounded" role="button">Modificar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
			</div>
		</section>
		<hr>
		<section id="compras">
			<div class="container">
				<br>
				<h1 id="title">COMPRAS POR USUARIO</h1>
				<br>
				<table class="table table-striped my_table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nombre de usuario</th>
							<th scope="col">Compras</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${users}" var="user">
							<c:if test="${!user.getAdmin()}">
								<tr>
									<th scope="row">-</th>
									<td><c:out value="${user.getNombre()}"></c:out></td>
						
									<td><c:out value="${user.getItinerario().getProductosItinerario()}"></c:out></td>

								</tr>
							</c:if>
						</c:forEach>
					
					</tbody>
				</table>
				<br>
			</div>
		</section>
		<br>
	</main>
	<script>
		$('.my_table').DataTable();
	</script>
</body>


</html>