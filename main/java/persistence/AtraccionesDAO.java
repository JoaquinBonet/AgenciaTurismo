package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Atraccion;
import model.Producto;
import persistence.commons.GenericDAO;

public interface AtraccionesDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByAtraccion(String nombre);
	public abstract int findIdByNombreAtraccion(String nombreAtraccion);
	public abstract Producto toAtraccion(ResultSet resultados) throws Exception;
	public abstract List<Producto> findByTipo(String tipo);
	public abstract List<Producto> findByNoTipo(String tipoString);
	
}
