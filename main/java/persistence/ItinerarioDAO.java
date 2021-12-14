package persistence;

import java.util.ArrayList;

import model.Itinerario;
import model.Producto;
import model.Usuario;
import persistence.commons.GenericDAO;


public interface ItinerarioDAO extends GenericDAO<Itinerario> {

	public abstract Itinerario toItinerario(ArrayList<Producto> p) throws Exception;

	

}
