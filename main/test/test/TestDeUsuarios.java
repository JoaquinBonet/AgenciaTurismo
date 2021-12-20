package test;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.PromocionAxB;
import model.Usuario;
import persistence.commons.DAOFactory;
import persistence.impl.UsuariosDAOImpl;

public class TestDeUsuarios {
	Usuario u;

	@Before
	public void setup() throws Exception {
		u = new Usuario("Juan Carlos", "AVENTURA", 10, 150, false);
	}

	@Test
	public void usuarioTest() throws Exception {
		Atraccion a = new Atraccion("Mountain Splash", 100, 10, 3, "", "AVENTURA", "");
		Atraccion b = new Atraccion("Acuatic", 90, 17, 2, "", "AVENTURA", "");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		UsuariosDAOImpl uDao = DAOFactory.getUsuariosDao();
		atracciones.add(a);
		atracciones.add(b);

		uDao.insert(u);
		Promocion p = new PromocionAxB(atracciones, "promo2X1", "", "");

		assertEquals(150, u.getPresupuesto());
		assertEquals(10, u.getTiempo(), 0.001);

		u.agregarAlItinerario(p);

		assertEquals(140, u.getPresupuesto());
		assertEquals(5, u.getTiempo(), 0.001);
		assertEquals(
				"\t" + a.getNombre(),
				u.getItinerario().getAtraccionesDeItinerario().get(0).toString());

		uDao.delete(u);
		
	}



}