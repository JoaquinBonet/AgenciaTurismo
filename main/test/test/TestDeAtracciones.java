package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Atraccion;

public class TestDeAtracciones {
	Atraccion atraccion;

	@Before
	public void Setup() throws Exception {
		atraccion = new Atraccion("PruebasAtracciones", 100, 10, 3, "", "ACUATICO", "");
	}

	@Test
	public void setearCosto() {
		atraccion.setCosto(500);
		assertEquals(500, atraccion.getCosto());
	}

	@Test
	public void agregarVisitanteYCupoLlenoTest() throws Exception {
		
		assertEquals(100, atraccion.getCupo());
		atraccion.agregarVisitantes(20);
		atraccion.agregarVisitantes(80);
		assertEquals(0, atraccion.getCupo());
		assertTrue(atraccion.estaLleno());
		
	}

	@Test
	public void getCodigoDeAtraccionAventura() throws Exception {
		Atraccion atraccion = new Atraccion("Scape Room", 50, 25, 50, "", "AVENTURA", "");
		assertEquals(1, atraccion.getCodigoAtraccion());
	}

	@Test
	public void getCodigoDeAtraccionPaisaje() throws Exception {
		Atraccion atraccion = new Atraccion("Scape Room", 50, 25, 50, "", "PAISAJE", "");
		assertEquals(2, atraccion.getCodigoAtraccion());
	}

	@Test
	public void getCodigoDeAtraccionDegustacion() throws Exception {
		Atraccion atraccion = new Atraccion("Scape Room", 50, 25, 50, "", "DEGUSTACION", "");
		assertEquals(3, atraccion.getCodigoAtraccion());
	}
}