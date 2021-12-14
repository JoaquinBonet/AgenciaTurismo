package services;

import java.util.HashMap;
import java.util.Map;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionesDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionesDAO;
import persistence.UsuariosDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAOImpl;

public class BuyProductService {

	AtraccionesDAO aDAO = DAOFactory.getAtraccionesDao();
	PromocionesDAO pDAO = DAOFactory.getPromocionesDao();
	UsuariosDAO uDAO = DAOFactory.getUsuariosDao();
	ItinerarioDAOImpl iDAO = DAOFactory.getItinerariosDao();

	public Map<String, String> buy(String nombreUsuario, String nombreProducto) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = uDAO.findByUsername(nombreUsuario);
		Producto producto = aDAO.findByAtraccion(nombreProducto);

		if (producto == null) {
			producto = pDAO.findByPromocion(nombreProducto);
		}

		if (producto.estaLleno()) {
			errors.put("producto", "No hay cupo disponible");
		}
		if (!user.puedePagar(producto)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.tieneTiempoPara(producto)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.agregarAlItinerario(producto);
			uDAO.update(user);
			iDAO.insert(user, producto);
			if (producto.esPromocion()) {
				pDAO.agregarVisitante((Promocion) producto);
			} else {
				producto.agregarVisitantes(1);
				aDAO.update((Atraccion) producto);
			}
		
		}

		return errors;

	}

}
