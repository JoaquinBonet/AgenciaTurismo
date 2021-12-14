package services;

import model.Itinerario;
import model.Usuario;
import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAOImpl;

public class ItinerarioService {
	ItinerarioDAOImpl iDao = DAOFactory.getItinerariosDao();
	
	public Itinerario findByUser(Usuario user) {
		return iDao.findItinerarioByUser(user);
	}

}
