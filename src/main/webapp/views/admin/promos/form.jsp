<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="name" name="name" required
		value="${promo.getNombre()}">
</div>
<div class="mb-3">
	<label for="img"
		class='col-form-label'>URL de imagen: </label>
	<input class="form-control" type="text" id="img" name="img"
		required value="${promo.getImg()}"></input>

</div>
<div class="mb-3" >
	<label for="tipo" class="col-form-label">Tipo de promoción:</label> <input
		type="text" class="form-control" id="tipo" name="tipo" required
		value="${promo.getClass().getSimpleName()}">
</div>
<div class="mb-3" style="display:none" id="div_costo">
	<label for="cost"
		class='col-form-label ${promo.errors.get("cost") != null ? "is-invalid" : "" }'>Costo fijo:</label>
	<input class="form-control" type="number" id="cost" name="cost"
		 value="${promo.getCosto()}"></input>
	<div class="invalid-feedback">
		<c:out value='${promo.errors.get("cost")}'></c:out>
	</div>
</div>

<div class="mb-3" style="display:none" id="div_porcentaje">
	<label for="porcentaje"
		class='col-form-label ${promo.errors.get("porcentaje") != null ? "is-invalid" : "" }'>Porcentaje de descuento:</label>
	<input class="form-control" type="number" id="porcentaje" name="porcentaje"
		 value="${promo.getCosto()}"></input>
	<div class="invalid-feedback">
		<c:out value='${promo.errors.get("porcentaje")}'></c:out>
	</div>
</div>
<div class="mb-3" style="display:none" id="div_atracciones"> 
	<label for="atracciones"
		class='col-form-label ${promo.errors.get("tipo") != null ? "is-invalid" : "" }'>Atracciones (por favor ingrese los nombres separadas por una coma. Por ejemplo: Moria, Mordor):</label>
	<input class="form-control" type="text" id="atracciones" name="atracciones"
		required value="${promo.getAtracciones()}"></input>
	<div class="invalid-feedback">
		<c:out value='${promo.errors.get("tipo")}'></c:out>
	</div>
</div>
<div class="mb-3" style="display:none" id="div_descripcion">
	<label for="info" class='col-form-label '>Descripción:</label> <input
		class="form-control" type="text" id="info" name="info" required
		value="${promo.getInfo()}"></input>
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
