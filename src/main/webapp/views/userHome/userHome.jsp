
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">


<head>

<jsp:include page="/partials/head.jsp"></jsp:include>

<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="userHome.css">
</head>

<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main>

		<section>
			<div class="container title">
				<div class="row">
					<br>
					<h1 id="title">
						¡Bienvenido/a,
						<c:out value="${user.nombre}" />
						!
					</h1>
					<br>

					<div class="container carousel" id="myCarousel">



						<div id="carouselExampleCaptions"
							class="carousel slide carousel-fade" data-bs-ride="carousel">
							<div class="carousel-inner">
								<c:forEach items="${productos}" var="pr">

									<c:if
										test="${user.puedePagar(pr) && user.tieneTiempoPara(pr) && !pr.estaLleno() &&
						!pr.estaEn(user.getItinerario().getProductosItinerario()) && !pr.estaEn(user.getItinerario().getAtraccionesDeItinerario())
						&& !pr.algunaAtracEstaEnItinerario(user.getItinerario().getAtraccionesDeItinerario())}">

										<div class="carousel-item">
											<a
												href="../itemDetail/itemDetail.jsp?producto=<c:out value="${pr.getNombre()}"></c:out>">

												<img src="${pr.getImg()}" class="d-block w-100" alt="...">
											</a>

											<div class="carousel-caption d-sm-block">
												<h5>
													<c:out value="${pr.getNombre()}"></c:out>
												</h5>
												<p>
													<c:out value="${pr.getInfo()}"></c:out>
												</p>
												<a
													href="../itemDetail/itemDetail.jsp?producto=<c:out value="${pr.getNombre()}"></c:out>"
													class="btn btn-dark">Ver más</a>
											</div>
										</div>
										<button class="carousel-control-prev" type="button"
											data-bs-target="#carouselExampleCaptions"
											data-bs-slide="prev">
											<span class="carousel-control-prev-icon" aria-hidden="true"></span>
											<span class="visually-hidden">Previous</span>
										</button>
										<button class="carousel-control-next" type="button"
											data-bs-target="#carouselExampleCaptions"
											data-bs-slide="next">
											<span class="carousel-control-next-icon" aria-hidden="true"></span>
											<span class="visually-hidden">Next</span>
										</button>
									</c:if>

								</c:forEach>
								<c:if
									test="${user.getPresupuesto() < 4 || user.getTiempo() < 2}">
									<div class="carousel-item">

										<img src="https://i.ibb.co/q58KCKv/Pack-Paisaje.jpg"
											class="d-block w-100" alt="...">

										<div class="carousel-caption d-sm-block">
											<h5>
												<c:out
													value="No tiene dinero y/o tiempo suficiente para comprar atracciones, por favor póngase en contacto con el administrador"></c:out>
											</h5>
										</div>
									</div>
								</c:if>
							</div>

						</div>
					</div>


				</div>
			</div>



		</section>
		<br>
	</main>
	<br>

	<jsp:include page="/partials/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#myCarousel').find('.carousel-item').first().addClass('active');
		});
	</script>
</body>

</html>