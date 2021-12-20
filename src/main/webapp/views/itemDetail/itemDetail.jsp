<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/partials/head.jsp"></jsp:include>

<link rel="stylesheet" href="<%= request.getContextPath() %>/itemDetail.css">
</head>

<body>
	<jsp:useBean id="aService" class="services.AttractionService" />
	<jsp:useBean id="pService" class="services.PromocionService" />
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<div class="container-fluid" id="itemDetail">

		<div class="row justify-content-center">

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

			<%
			String user = request.getParameter("user");
			String atraccion = request.getParameter("producto");
			pageContext.setAttribute("p", aService.find(atraccion));
			if ( pageContext.getAttribute("p") == null) {
				String promo = request.getParameter("producto");
				pageContext.setAttribute("p", pService.find(promo));

			} 
			%>

			<div class="col-md-7">
				<img src="${p.getImg()}" alt="${p.getNombre()}">
			</div>
			<div class="col-md-5">
				<h1>
					<c:out value="${p.getNombre()}"></c:out>
				</h1>
				<br>
				<p>
					<c:out value="${p.getInfo()}"></c:out>
				</p>
				<br>
				<c:if test="${p.esPromocion()}">
					<p>Esta promoción incluye las siguientes atracciones:</p>
					<ul>
						<c:forEach items="${p.getAtracciones()}" var="atraccion">
							<li>- <c:out value="${atraccion.getNombre()}"></c:out></li>
						</c:forEach>
					</ul>
				</c:if>
				<ul>
					<li>Tipo: <c:out value="${p.getTipo()}"></c:out></li>
					<li>Duración: <c:out value="${p.getDuracion()}"></c:out></li>
					<li>Costo: <c:out value="${p.getCosto()}"></c:out></li>
				</ul>

				<c:choose>

					<c:when
						test="${user.puedePagar(p) && user.tieneTiempoPara(p) && !p.estaLleno() &&
						!p.estaEn(user.getItinerario().getProductosItinerario()) && !p.estaEn(user.getItinerario().getAtraccionesDeItinerario())
						&& !p.algunaAtracEstaEnItinerario(user.getItinerario().getAtraccionesDeItinerario())}">
						<button type="button" class="btn btn-dark" data-bs-toggle="modal"
							data-bs-target="#exampleModal">Comprar</button>

					</c:when>
					<c:otherwise>
						<a href="#" class="btn btn-secondary rounded disabled"
							role="button">No se puede comprar</a>
					</c:otherwise>
				</c:choose>

				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content" id="modal">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">¿Desea proceder con la compra?</div>
							<div class="modal-footer">
								<a
									href="/AgenciaTurismo/views/itemDetail/itemDetail/buy.do?nombre=${p.getNombre()}"
									class="btn btn-success" role="button">Comprar</a>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<jsp:include page="/partials/footer.jsp"></jsp:include>

	<script>
		var myModal = document.getElementById('myModal')
		var myInput = document.getElementById('myInput')

		myModal.addEventListener('shown.bs.modal', function() {
			myInput.focus()
		})
	</script>
</body>

</html>