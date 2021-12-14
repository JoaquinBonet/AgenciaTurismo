<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="name" name="name"
		required value="${attraction.getNombre()}">
</div>
<div class="mb-3">
	<label for="cost"
		class='col-form-label ${attraction.errors.get("cost") != null ? "is-invalid" : "" }'>Costo:</label>
	<input class="form-control" type="number" id="cost" name="cost"
		required value="${attraction.getCosto()}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.errors.get("cost")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="duration"
		class='col-form-label ${attraction.errors.get("duration") != null ? "is-invalid" : "" }'>Duration:</label>
	<input class="form-control" type="number" id="duration" name="duration"
		required value="${attraction.getDuracion()}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.errors.get("duration")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="capacity"
		class='col-form-label ${attraction.errors.get("capacity") != null ? "is-invalid" : "" }'>Capacity:</label>
	<input class="form-control" type="number" id="capacity" name="capacity"
		required value="${attraction.getCupo()}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.errors.get("capacity")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="info"
		class='col-form-label '>Descripción:</label>
	<input class="form-control" type="text" id="info" name="info"
		required value="${attraction.getInfo()}"></input>
</div>

<div class="mb-3">
	<label for="tipo"
		class='col-form-label ${attraction.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo de atracción:</label>
	<input class="form-control" type="text" id="tipo" name="tipo"
		required value="${attraction.tipo}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.errors.get("tipo")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="img"
		class='col-form-label'>URL de imagen: </label>
	<input class="form-control" type="text" id="img" name="img"
		required value="${attraction.getImg()}"></input>

</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
