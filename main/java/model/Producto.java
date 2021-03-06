package model;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Producto {

	public String tipo;
	public int costo;
	public double duracion;
	private String nombre;

	public Producto(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public boolean estaEn(ArrayList<Producto> productos) {
		boolean encontrado = false;

		for (Producto p : productos) {
			if (p.getNombre().equals(this.getNombre())) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public abstract ArrayList<Atraccion> getAtracciones();

	public void setDuracion(int duracion) {
		if (duracion <= 0)
			throw new Error("Duraci�n inv�lida");
		this.duracion = duracion;
	}

	public int getCosto() {
		return costo;
	}

	public String getTipo() {
		return this.tipo;
	};

	public boolean esPromocion() {
		return this instanceof Promocion;
	}

	public abstract void agregarVisitantes(int cantidad);

	public abstract boolean estaLleno();

	public abstract void setCosto(int costo);
	

	@Override
	public int hashCode() {
		return Objects.hash(costo, duracion, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return costo == other.costo && Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "\t" + this.getNombre();
	}
	
	public abstract boolean algunaAtracEstaEnItinerario(ArrayList<Producto> p);
}