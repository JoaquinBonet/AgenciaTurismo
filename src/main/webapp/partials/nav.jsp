<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<nav class="navbar navbar-expand-lg navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/AgenciaTurismo/views/userHome/userHome.do">Tierra
				Media</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/AgenciaTurismo/views/userHome/userHome.do">
							Sugerencias</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/AgenciaTurismo/views/totalProducts/totalProducts.do">Todos los productos</a>
					</li>


				</ul>
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a id="logIn"
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"
						data-bs-toggle="dropdown"> <span class="material-icons">
								person </span> <c:out value="${user.nombre}" />
					</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">

							<p>
								<span class="material-icons"> paid</span>
								<c:out value="${user.presupuesto}" />

							</p>

							<p>
								<span class="material-icons"> schedule </span>
								<c:out value="${user.getTiempo()}" />
							</p>
							<a class="nav-link" href="/AgenciaTurismo/views/itinerario/itinerario.jsp"
								tabindex="-1" aria-disabled="true">Mi itinerario</a> <a
								class="nav-link" href="../../logout" tabindex="-1"
								aria-disabled="true">Cerrar sesión</a>
						</div></li>

				</ul>
			</div>
		</div>
	</nav>
	<hr>