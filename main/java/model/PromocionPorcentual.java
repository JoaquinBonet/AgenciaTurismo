package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PromocionPorcentual extends Promocion {

	public int porcentajeDescuento;
	String img;
	Map<String, String> errors;

	public PromocionPorcentual(ArrayList<Atraccion> atracciones, String nombre, String info, int porcentajeDescuento,
			String img) {
		super(atracciones, nombre, info);
		this.porcentajeDescuento = porcentajeDescuento;
		if (atracciones.size() > 1) {
			this.setCosto(costo);
		} else {
			this.costo = 0;
		}
		
		this.img = img;
	}

	@Override
	public void setCosto(int costo) {
		int acumulador = 0;
		for (int i = 0; i < atracciones.size(); i++) {
			acumulador += atracciones.get(i).getCosto();
		}
		acumulador -= Math.round((acumulador * this.porcentajeDescuento) / 100);
		this.costo = acumulador;
	}

	public int getPorcentaje() {

		return this.porcentajeDescuento;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public boolean isValid() {
		validate();
		return errors.isEmpty();

	}

	@Override
	public void validate() {

		errors = new HashMap<String, String>();

		if (porcentajeDescuento <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (atracciones.size() <= 1) {
			errors.put("atracciones", "Debe haber más de una atracción");
		}
		if (!chequearTipoDeAtracciones()) {
			errors.put("tipo", "Las atracciones deben ser del mismo tipo");
		}

	}

	@Override
	public Map<String, String> getErrors() {
		return errors;
	}
	
	@Override
	public void setAtracciones(ArrayList<Atraccion> atracciones2) {
		this.atracciones = atracciones2;
		
	}
	
	@Override
	public void setPorcentaje(Integer porcentaje) {
		this.porcentajeDescuento = porcentaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(errors, img, porcentajeDescuento);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionPorcentual other = (PromocionPorcentual) obj;
		return Objects.equals(errors, other.errors) && Objects.equals(img, other.img)
				&& porcentajeDescuento == other.porcentajeDescuento;
	}
	
	@Override
	public boolean algunaAtracEstaEnItinerario(ArrayList<Producto> atracciones) {
		boolean rta = false;
		for (Atraccion a : this.getAtracciones()) {
			if (a.estaEn(atracciones)) {
				rta = true;
			}
		} return rta;
	} 
}