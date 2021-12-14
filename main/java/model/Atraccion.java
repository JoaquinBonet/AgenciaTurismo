package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import persistence.commons.DAOFactory;
import persistence.impl.TipoDAOImpl;
import services.TipoService;

public class Atraccion extends Producto {

	public int cupo;
	public boolean estaLleno;
	public String nombre;
	public String info;
	public String img_url;
	

	private Map<String, String> errors;

	public Atraccion(String nombre, int cupo, int costo, double duracion, String info, String tipo, String img_url)
			 {
		super(nombre);
	
		setCosto(costo);
		this.duracion = duracion;
		this.cupo = cupo;
		this.tipo = tipo;
		this.info = info;
		this.nombre = nombre;
		this.img_url = img_url;
	}

	public boolean puedeHostear(int i) {
		return cupo >= i;
	}

	@Override
	public void agregarVisitantes(int cantidad) {
		cupo -= cantidad;

	}

	@Override
	public void setCosto(int costo) {
		
		this.costo = costo;
	}

	@Override
	public boolean estaLleno() {
		return this.cupo == 0;
	}

	public int getCupo() {
		return this.cupo;
	}
	
	public void setCupo(int cupo) {
		 this.cupo = cupo;
	}

	public double getDuracion() {
		return this.duracion;
	}
	
	public void setDuracion(double duracion) {
		 this.duracion = duracion;
	}

	@Override
	public ArrayList<Atraccion> getAtracciones() {
		return null;
	}

	public int getCodigoAtraccion() {
		TipoDAOImpl tDao = DAOFactory.getTipoDao();
		return 	tDao.findIdByNombre(this.getTipo());
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		TipoService tService = new TipoService();
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duracion <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (cupo <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
		
		if (!tService.exists(tipo)) {
			errors.put("tipo", "Debe ser un tipo existente");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getImg() {
		return img_url;
	}

	public void setImg(String img_url) {
		this.img_url = img_url;
	}

	@Override
	public boolean algunaAtracEstaEnItinerario(ArrayList<Producto> atracciones) {
	return false;
	} 

}