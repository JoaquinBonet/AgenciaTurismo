package persistence;

import java.sql.ResultSet;
import java.util.List;

import model.Atraccion;
import model.Producto;
import persistence.commons.GenericDAO;

public interface TipoDAO extends GenericDAO<String> {

	public abstract String findByNombre(String nombre);
	public abstract int findIdByNombre(String nombre);
	int insert(String t);
	int delete(String t);
	public abstract boolean exists(String nombre);
	

	
}