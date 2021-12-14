const inputTipo = document.querySelector("#tipo");
const inputInfo = document.querySelector("#info");
const inputAtracciones = document.querySelector("#atracciones");
const inputCosto = document.querySelector("#cost");
const inputPorcentaje = document.querySelector("#porcentaje");

const div_atracciones = document.querySelector("#div_atracciones");
const div_info = document.querySelector("#div_descripcion");
const div_porcentaje = document.querySelector("#div_porcentaje");
const div_costofijo =  document.querySelector("#div_costo");

const discriminarTipo = () => {
	if (inputTipo.value.toLowerCase() == 'porcentual' ||
	inputTipo.value.toLowerCase() == 'promocion porcentual'){
		div_atracciones.style.display = "block";
		div_info.style.display = "block";
		div_porcentaje.style.display = "block";
		div_costo.style.display = "none";
	}
	
	if  (inputTipo.value.toLowerCase() == 'absoluta' ||
	inputTipo.value.toLowerCase() == 'promocion absoluta'){
		div_atracciones.style.display = "block";
		div_info.style.display = "block";
			div_costo.style.display = "block";
			div_porcentaje.style.display = "none";
	}
	
	if  (inputTipo.value.toLowerCase() == 'axb' ||
	inputTipo.value.toLowerCase() == 'promocion axb'){
		div_atracciones.style.display = "block";
		div_info.style.display = "block";
		div_costo.style.display = "none";
			div_porcentaje.style.display = "none";
	}
}


inputTipo.addEventListener("change", () => discriminarTipo());