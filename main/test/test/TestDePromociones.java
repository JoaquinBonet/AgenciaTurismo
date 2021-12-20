package test;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;

public class TestDePromociones {

	ArrayList<Atraccion> atracciones;

	@Before
	public void Before() {
		atracciones = new ArrayList<Atraccion>();
	}

	@Test
	public void promocionAxBTest() throws Exception {
		Atraccion a = new Atraccion("Toboganes Acuaticos", 80, 50, 25, "", "ACUATICO", "");
		Atraccion a2 = new Atraccion("Trampolin", 70, 15, 5, "", "ACUATICO", "");
		assertEquals(70, a2.getCupo());

		atracciones.add(a);
		atracciones.add(a2);

		Producto p = new PromocionAxB(atracciones, "Promo Acuatica", "", "");

		assertEquals(50, p.getCosto());
		assertEquals(30, 0, p.getDuracion());
		p.agregarVisitantes(15);
		assertEquals(65, a.getCupo());
		assertEquals(55, a2.getCupo());
	}


	@Test
	public void duracionTest() throws Exception {
		Atraccion c = new Atraccion("La Comarca", 15, 15, 40,  "", "AVENTURA", "");
		Atraccion d = new Atraccion("Abismo de Helm", 30, 52, 40,  "", "AVENTURA", "");

		atracciones.add(c);
		atracciones.add(d);
		Producto axb = new PromocionAxB(atracciones, "Promo Aventura", "", "");
		Producto pp = new PromocionPorcentual(atracciones, "Promo Aventura", "", 10, "");
		Producto pa = new PromocionAbsoluta(atracciones, "Promo Aventura", "", 23, "");

		assertEquals(80, axb.getDuracion(), 0.00001);
		assertEquals(80, pp.getDuracion(), 0.00001);
		assertEquals(80, pa.getDuracion(), 0.00001);
		
	}

	@Test
	public void promoAbsolutaTest() throws Exception {
		Atraccion c = new Atraccion("La Comarca", 150, 15, 40,  "", "AVENTURA", "");

		Atraccion d = new Atraccion("Abismo de Helm", 60, 52, 40,  "", "AVENTURA", "");

		atracciones.add(c);
		atracciones.add(d);

		Promocion pAbs = new PromocionAbsoluta(atracciones, "promo absoluta", "", 60, "");

		assertEquals(15, c.getCosto());
		assertEquals(52, d.getCosto());

		assertTrue((c.getCosto() + d.getCosto()) > pAbs.getCosto());

		assertEquals(60, pAbs.getCosto());

	}


	@Test
	public void promoPorcentualTest() throws Exception {


		Atraccion c = new Atraccion("La Comarca", 150, 15, 40,  "", "AVENTURA", "");
		Atraccion d = new Atraccion("Abismo de Helm", 60, 52, 40,  "", "AVENTURA", "");

		atracciones.add(c);
		atracciones.add(d);

		Promocion p3 = new PromocionPorcentual(atracciones, "promo porcentual", "", 10, "");

		assertEquals(61, p3.getCosto());
		assertEquals(55, 0, p3.getDuracion());
		p3.agregarVisitantes(15);
		assertFalse(p3.estaLleno());
	}

}