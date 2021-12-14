package services;

import java.util.List;

import model.Atraccion;
import model.Producto;
import persistence.AtraccionesDAO;
import persistence.TipoDAO;
import persistence.commons.DAOFactory;

public class TipoService {
	
	public List<String> list() {
		return DAOFactory.getTipoDao().findAll();
	}

	public String create(String nombre) {
		
			TipoDAO tDAO = DAOFactory.getTipoDao();
			tDAO.insert(nombre);
			return nombre;

	}


	public void delete(String nombre) {

		TipoDAO tDAO = DAOFactory.getTipoDao();
		tDAO.delete(nombre);
	}

	public String findByNombre(String nombre) {
		TipoDAO tDAO = DAOFactory.getTipoDao();
	
		return tDAO.findByNombre(nombre);
	}
	
	public boolean exists(String nombre) {
		TipoDAO tDAO = DAOFactory.getTipoDao();
		
		return tDAO.exists(nombre);
	}
	
	public static void main(String[] args) {
		TipoService tService = new TipoService();
		tService.exists("AVENTURA");
	}

}
