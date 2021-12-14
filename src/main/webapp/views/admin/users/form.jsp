<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
	<label for="name" class="col-form-label">Nombre de usuario:</label> <input
		type="text" class="form-control" id="name" name="name"
		required value="${user.getNombre()}">
</div>

<div class="mb-3">
	<label for="coins"
		class='col-form-label ${user.errors.get("coins") != null ? "is-invalid" : "" }'>Monedas:</label>
	<input class="form-control" type="number" id="coins" name="coins"
		required value="${user.getPresupuesto()}"></input>
	<div class="invalid-feedback">
		<c:out value='${user.errors.get("coins")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="time"
		class='col-form-label ${user.errors.get("time") != null ? "is-invalid" : "" }'>Tiempo:</label>
	<input class="form-control" type="number" id="time" name="time"
		required value="${user.getTiempo()}"></input>
	<div class="invalid-feedback">
		<c:out value='${user.errors.get("time")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="tipo"
		class='col-form-label ${user.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo de atracci�n preferida:</label>
	<input class="form-control" type="text" id="tipo" name="tipo"
		required value="${user.getTipo()}"></input>
	<div class="invalid-feedback">
		<c:out value='${user.errors.get("tipo")}'></c:out>
	</div>
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>