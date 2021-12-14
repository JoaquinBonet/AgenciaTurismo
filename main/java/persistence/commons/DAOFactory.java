package persistence.commons;

import persistence.impl.AtraccionesDAOImpl;
import persistence.impl.ItinerarioDAOImpl;
import persistence.impl.PromocionesDAOImpl;
import persistence.impl.TipoDAOImpl;
import persistence.impl.UsuariosDAOImpl;

public class DAOFactory {
	
	public static UsuariosDAOImpl getUsuariosDao() {
		return new UsuariosDAOImpl();
	}
	public static AtraccionesDAOImpl getAtraccionesDao() {
		return new AtraccionesDAOImpl();
	}
	public static PromocionesDAOImpl getPromocionesDao() {
		return new PromocionesDAOImpl();
	}

	public static ItinerarioDAOImpl getItinerariosDao() {
		return new ItinerarioDAOImpl();
	}
	
	public static TipoDAOImpl getTipoDao() {
		return new TipoDAOImpl();
	}
}

