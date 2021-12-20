package test;

import static org.junit.Assert.*;


import org.junit.Test;

import model.Atraccion;
import model.Producto;
import persistence.commons.DAOFactory;
import persistence.impl.AtraccionesDAOImpl;


public class TestDeAtraccionesDAO {

	AtraccionesDAOImpl aDao = DAOFactory.getAtraccionesDao();

	@Test
	public void findAllTest() {
		assertEquals(8, aDao.findAll().size());

	}

	@Test
	public void findByTipoAtraccionTest() throws Exception {
		Producto p = new Atraccion("Moria", 18, 10, 2, "", "AVENTURA", "");
		assertEquals(p.getNombre(), aDao.findByAtraccion("Moria").getNombre());
		assertEquals(p.getTipo(), aDao.findByAtraccion("Moria").getTipo());

	}

	@Test
	public void findByIdDTest() {
		assertEquals(8, aDao.findIdByNombreAtraccion("Bosque Negro"));
	}

	@Test
	public void insertDeleteTest() throws Exception {
		Producto a1 = new Atraccion("p", 15, 15, 15, "", "AVENTURA", "");

		aDao.insert((Atraccion) a1);
		assertEquals(9, aDao.countAll());

		aDao.delete((Atraccion) a1);
		assertEquals(8, aDao.countAll());

	}

	@Test
	public void findByAtraccionTest() throws Exception {

		Producto a1 = new Atraccion("Mordor", 15, 16, 15, "", "AVENTURA", "");

		assertEquals(a1.getNombre(), aDao.findByAtraccion("Mordor").getNombre());
	}
}
