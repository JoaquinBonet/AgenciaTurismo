package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import persistence.commons.DAOFactory;
import persistence.impl.TipoDAOImpl;
import services.TipoService;

public abstract class Promocion extends Producto {

	ArrayList<Atraccion> atracciones;
	String nombre;
	String info;
	private String img;
	
	public Promocion(ArrayList<Atraccion> atracciones, String nombre, String info) {
		super(nombre);
		this.atracciones = atracciones;
		if (atracciones.size() > 1) {
			this.tipo =  atracciones.get(0).getTipo();
		} else {
			this.tipo = "";
		}
		
		if (atracciones.size() > 1) {
			setDuracion(this.atracciones);
		} else {
			this.duracion = 0;
		}
		this.info = info;
	}

	
	public int getCosto() {
		return costo;
	}

	public double getDuracion() {
		return this.duracion;
	}

	
	public void setDuracion(ArrayList<Atraccion> atrac) {
		double acumulador = 0;
		for (int i = 0; i < atrac.size(); i++) {
			acumulador += atrac.get(i).getDuracion();
		}
		this.duracion = acumulador;
	}

	@Override
	public boolean estaLleno() {
		boolean rta = false;
		for (int i = 0; i < atracciones.size(); i++) {
			if (atracciones.get(i).estaLleno())
				rta = true;
		}
		return rta;
	}

	@Override
	public void agregarVisitantes(int cantidad) {
		for (int i = 0; i < atracciones.size(); i++) {
			if (cantidad  > atracciones.get(i).getCupo())
				throw new Error("Demasiados visitantes para el cupo");

			atracciones.get(i).agregarVisitantes(cantidad);

		}
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	public int getIdTipoProducto(Promocion p) {
		return 2;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}
	
	public int getCodigoAtraccion() {
		TipoDAOImpl tDao = DAOFactory.getTipoDao();
		return 	tDao.findIdByNombre(this.getTipo());
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public abstract boolean isValid();

	
	public abstract void validate();
	
	public abstract Map<String, String> getErrors();


	public abstract int getPorcentaje();


	public abstract void setAtracciones(ArrayList<Atraccion> atracciones2);


	public abstract void setPorcentaje(Integer porcentaje);
	
	public boolean chequearTipoDeAtracciones() {
		boolean rta = true;
		for (Atraccion ca : this.getAtracciones()){ 
			if(!ca.getTipo().equals(this.getTipo())) {
				rta = false;
			}
		}
		return rta;
	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atracciones, img, info, nombre);
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
		Promocion other = (Promocion) obj;
		return Objects.equals(atracciones, other.atracciones) && Objects.equals(img, other.img)
				&& Objects.equals(info, other.info) && Objects.equals(nombre, other.nombre);
	}

}