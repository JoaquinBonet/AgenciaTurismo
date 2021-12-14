package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Producto;
import model.Promocion;
import persistence.commons.GenericDAO;


public interface PromocionesDAO extends GenericDAO<Promocion>  {
	
	public abstract int findIdByNombrePromocion(String nombrePromo);
	public abstract Promocion findByPromocion(String nombre);
	public abstract Promocion toPromocion(ResultSet resultados) throws SQLException;
	public abstract List<Producto> findByTipo(String tipoString);
	public abstract List<Producto> findByNoTipo(String tipoString);
	public abstract int deleteAtracPromo(String name);
	public abstract void agregarVisitante(Promocion p);

}
