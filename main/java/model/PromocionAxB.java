package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PromocionAxB extends Promocion {
	Atraccion atraccionGratis;
	String img;
	Map<String, String> errors;

	public PromocionAxB(ArrayList<Atraccion> atracciones, String nombre, String info, String img) {
		super(atracciones, nombre, info);
	
		this.atraccionGratis = atracciones.get(atracciones.size() - 1);
		this.setCosto(costo);
		this.img = img;
	}

	@Override
	public void setCosto(int costo) {
		int acumulador = 0;
		for (int i = 0; i < atracciones.size() - 1; i++) {
			acumulador += atracciones.get(i).getCosto();
		}
		this.costo = acumulador;
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