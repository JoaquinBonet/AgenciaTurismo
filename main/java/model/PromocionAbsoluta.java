package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import services.TipoService;

public class PromocionAbsoluta extends Promocion {

	public int costoFijo;
	public String img;
	Map<String, String> errors;

	public PromocionAbsoluta(ArrayList<Atraccion> atracciones, String nombre, String info, int costoFijo, String img) {
		super(atracciones, nombre, info);

		this.costoFijo = costoFijo;		
		this.img = img;
	}

	@Override
	public void setCosto(int costo) {
	
			this.costo = costo;
		
	}

	public int getCosto() {
		
		return this.costoFijo;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public int getPorcentaje() {
		return 0;
	}

	@Override
	public boolean isValid() {
		validate();
		return errors.isEmpty();
		
	}

	@Override
	public void validate() {
		
		errors = new HashMap<String, String>();

		if (costoFijo <= 0) {
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
		
		
	}
	
	public void setCostoFijo( int costo) {
		this.costoFijo = costo;
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