<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<link rel="stylesheet" href="itinerario.css">
</head>

<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>
	<br>
	<main>
		<section>
			<div class="container">
				<h1 id="title">ITINERARIO</h1>
				<p id="text">A continuación se presenta un resumen de su compra
					y las atracciones que visitará</p>
			</div>
		</section>

		<br>
		<section>
			<div class="container">
			
				<div class="table-responsive-sm">
					<table id="my_table" class="table table-dark">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Atracción</th>
								<th scope="col">Tipo De Atraccion</th>
								<th scope="col">Duración</th>
								<th scope="col">Costo</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach
								items="${user.getItinerario().getAtraccionesDeItinerario()}"
								var="at">
								<tr>
									<th scope="row">-</th>
									<td><c:out value="${at.nombre}"></c:out></td>
									<td><c:out value="${at.tipo}"></c:out></td>
									<td><c:out value="${at.duracion}"></c:out></td>
									<td><c:out value="${at.costo}"></c:out></td>
								</tr>
							</c:forEach>
							<tr>
								<th colspan="3">Suma</th>
								<td><c:out
										value="${user.getItinerario().calcularTiempoItinerario()}"></c:out></td>
								<td><c:out
										value="${user.getItinerario().calcularSumaCostoAtracciones()}"></c:out></td>
							</tr>
							<tr>
								<th colspan="3">Descuento por promoción</th>

								<td></td>
								<td><c:out
										value="${user.getItinerario().calcularAhorroDeItinerario()}"></c:out></td>
							</tr>
							<tr>
								<th colspan="3">TOTAL</th>

								<td><c:out
										value="${user.getItinerario().calcularTiempoItinerario()}"></c:out></td>
								<td><c:out
										value="${user.getItinerario().calcularCostoItinerario()}"></c:out></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</section>
	</main>


<jsp:include page="/partials/footer.jsp"></jsp:include>

</body>

</html>