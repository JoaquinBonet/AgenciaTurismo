package model;

import java.util.HashMap;
import java.util.Map;

import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAOImpl;
import persistence.impl.TipoDAOImpl;
import persistence.impl.UsuariosDAOImpl;
import services.TipoService;

public class Usuario {

	private int presupuesto = 0;
	private double tiempoDisponible = 0;
	private String tipoDeAtraccionPreferida;
	String nombre;
	public Itinerario itinerario;
	ItinerarioDAOImpl itinerarioDao = new ItinerarioDAOImpl();
	UsuariosDAOImpl uDao = DAOFactory.getUsuariosDao();
	Boolean admin;

	private HashMap<String, String> errors; 


	public Usuario(String nombre, String tipoDeAtraccionPreferida, double tiempoDisponible, int presupuesto, Boolean admin) {
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.nombre = nombre;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		this.admin = admin;

		if (uDao.existe(this)) {
			if (itinerarioDao.existe(this)) {
				this.itinerario = itinerarioDao.findItinerarioByUser(this);
			} else {
				this.itinerario = new Itinerario();
			}
		} else {
			this.itinerario = new Itinerario();
		}
		
	}

	public boolean puedePagar(Producto p) {
		return p.getCosto() <= this.getPresupuesto();
	}
	
	public boolean tieneTiempoPara(Producto p) {
		return p.getDuracion() <= this.getTiempo();
	}
	
	public int getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempo() {
		return this.tiempoDisponible;
	}

	public String getTipo() {
		return this.tipoDeAtraccionPreferida;
	}

	public int getIdTipoAtraccion() {
	TipoDAOImpl tDao = DAOFactory.getTipoDao();
		return 	tDao.findIdByNombre(this.getTipo());
	}

	public void setTiempo(double tiempo) {

		this.tiempoDisponible = tiempo;
	}

	public void setPresupuesto(int presupuesto) {


		this.presupuesto = presupuesto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void agregarAlItinerario(Producto producto) {
		System.out.println(producto);
		this.itinerario.getProductosItinerario().add(producto);
		System.out.println("entró");
		System.out.println(this.getItinerario().getProductosItinerario());
		this.presupuesto -= producto.getCosto();
		this.tiempoDisponible -= producto.getDuracion();
	}

	public Itinerario getItinerario() {
		return this.itinerario;
	}


	public boolean isNull() {
		return false;
	}

	public Boolean getAdmin() {
		return admin;
	}


	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		TipoService tService = new TipoService();
		errors = new HashMap<String, String>();

		if (this.getPresupuesto() < 0) {
			errors.put("coins", "No debe ser negativo");
		}
		if (this.getTiempo() < 0) {
			errors.put("time", "No debe ser negativo");
		}
		if (!tService.exists(this.getTipo())) {
			errors.put("tipo", "Debe ser un tipo existente");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setTipo(String tipo) {
		this.tipoDeAtraccionPreferida = tipo;
		
	}

}