package services;

import java.util.List;

import model.Atraccion;
import model.Producto;
import persistence.AtraccionesDAO;
import persistence.TipoDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionesDao().findAll();
	}

	public Atraccion create(String nombre, int cupo, int costo, double duracion, String info, String tipo, String img) {

		Atraccion attraction = new Atraccion(nombre, cupo, costo, duracion, info, tipo, img);

		System.out.println(attraction.getTipo());
		if (attraction.isValid()) {
			AtraccionesDAO aDAO = DAOFactory.getAtraccionesDao();
			aDAO.insert(attraction);

		}

		return attraction;
	}

	public Atraccion update(String nombre, int cupo, int costo, double duracion, String info, String tipo, String img) {

		AtraccionesDAO aDAO = DAOFactory.getAtraccionesDao();
		Atraccion atraccion = aDAO.findByAtraccion(nombre);

		atraccion.setCosto(costo);
		atraccion.setDuracion(duracion);
		atraccion.setCupo(cupo);
		atraccion.setInfo(info);
		atraccion.setTipo(tipo);
		atraccion.setImg(img);

		if (atraccion.isValid()) {
			aDAO.update(atraccion);

		}

		return atraccion;
	}

	public void delete(String nombre) {

		AtraccionesDAO aDAO = DAOFactory.getAtraccionesDao();
		Atraccion atraccion = aDAO.findByAtraccion(nombre);
		aDAO.delete(atraccion);
	}

	public Producto find(String nombre) {
		AtraccionesDAO attractionDAO = DAOFactory.getAtraccionesDao();
		System.out.println(nombre);
		return attractionDAO.findByAtraccion(nombre);
	}

	public List<Producto> findByTipo(String tipo) {
		String tipoString = tipo.toString();
		AtraccionesDAO attractionDAO = DAOFactory.getAtraccionesDao();
		return attractionDAO.findByTipo(tipoString);
	}

	public List<Producto> findByNoTipo(String preferencia) {
		String tipoString = preferencia.toString();
		AtraccionesDAO attractionDAO = DAOFactory.getAtraccionesDao();
		return attractionDAO.findByNoTipo(tipoString);
	}

	public static void main(String[] args) {
		AttractionService aService = new AttractionService();

	}

}
