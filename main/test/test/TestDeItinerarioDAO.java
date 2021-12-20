package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Atraccion;
import model.Itinerario;
import model.Producto;
import model.Promocion;
import model.PromocionAxB;
import model.Usuario;
import persistence.commons.DAOFactory;
import persistence.impl.AtraccionesDAOImpl;
import persistence.impl.ItinerarioDAOImpl;
import persistence.impl.PromocionesDAOImpl;
import persistence.impl.UsuariosDAOImpl;


public class TestDeItinerarioDAO {

	ItinerarioDAOImpl iDao = DAOFactory.getItinerariosDao();
	UsuariosDAOImpl uDao = DAOFactory.getUsuariosDao();
	AtraccionesDAOImpl aDao = DAOFactory.getAtraccionesDao();
	PromocionesDAOImpl pDao = DAOFactory.getPromocionesDao();

	@Test
	public void existeYdeleteTest() throws Exception {
		Usuario carlos = new Usuario("Carlos", "PAISAJE", 150, 150, false);
		uDao.insert(carlos);
		Producto p = new Atraccion("playa", 12, 12, 12, "", "PAISAJE", "");
		aDao.insert((Atraccion) p);
		iDao.insert(carlos, p);

		assertTrue(iDao.existe(carlos));

		iDao.delete(carlos);

		aDao.delete((Atraccion) p);
		assertFalse(iDao.existe(carlos));
		uDao.delete(carlos);

	}

	@Test
	public void findAtraccionesItinerarioTest() throws Exception {
		Usuario carlos = new Usuario("Carlos", "PAISAJE", 150, 150, false);
		uDao.insert(carlos);
		Producto p = new Atraccion("playa", 12, 12, 12, "", "PAISAJE", "");
		aDao.insert((Atraccion) p);
		iDao.insert(carlos, p);

		assertEquals(p, iDao.findAtraccionesItinerarioByUser(carlos).get(0));
		assertEquals(1, iDao.findAtraccionesItinerarioByUser(carlos).size());

		iDao.delete(carlos);

		aDao.delete((Atraccion) p);

		uDao.delete(carlos);

	}

	@Test
	public void findItinerarioTest() throws Exception {
		Usuario carlos = new Usuario("Carlos", "PAISAJE", 150, 150, false);
		uDao.insert(carlos);
		Producto a = new Atraccion("babababa", 12, 12, 12, "", "PAISAJE", "");
		Producto a1 = new Atraccion("masdfas", 12, 12, 12, "", "PAISAJE", "");
		Producto a2 = new Atraccion("asdfdssa", 12, 12, 12, "", "PAISAJE", "");

		aDao.insert((Atraccion) a);
		aDao.insert((Atraccion) a1);
		aDao.insert((Atraccion) a2);
		
		iDao.insert(carlos, a);
		iDao.insert(carlos, a1);
		iDao.insert(carlos, a2);
		ArrayList<Producto> prod = new ArrayList();
		prod.add(a);
		prod.add(a1);
		prod.add(a2);
		Itinerario i = new Itinerario(prod);
;
		assertEquals(i.getProductosItinerario().get(0).getNombre(),
				iDao.findItinerarioByUser(carlos).getProductosItinerario().get(0).getNombre());

		uDao.delete(carlos);

	}

}
