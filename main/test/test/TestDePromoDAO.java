package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.PromocionAxB;
import model.PromocionPorcentual;
import persistence.commons.DAOFactory;
import persistence.impl.PromocionesDAOImpl;


public class TestDePromoDAO {

	PromocionesDAOImpl PromocionDao = DAOFactory.getPromocionesDao();
	
	@Test
	public void findIdTest() {
		
		assertEquals(1, PromocionDao.findIdByNombrePromocion("PackAventura"));
		assertEquals(2, PromocionDao.findIdByNombrePromocion("PackDegustacion"));
		assertEquals(3, PromocionDao.findIdByNombrePromocion("PackPaisaje"));
	}
	
	@Test 
	public void toPromocionTest() {
		assertEquals(3, PromocionDao.findAll().size());
	
	}

	
	@Test 
	public void findAtraccionesDePromocionTest() {
		
		assertEquals(3, PromocionDao.findAtraccionesDePromocion("PromocionAxB", "PackPaisaje").size());
		
		
		assertEquals(2, PromocionDao.findAtraccionesDePromocion("PromocionAbsoluta","PackDegustacion").size());
		
		assertEquals(2, PromocionDao.findAtraccionesDePromocion("PromocionPorcentual", "PackAventura").size());
	
				
	}
	
	@Test
	public void insertDeleteTest() throws Exception {
		Producto a1 = new Atraccion("playa", 15, 15, 15, "", "AVENTURA", "");
		Producto a2 = new Atraccion("montana", 15, 15, 15, "", "AVENTURA", "");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add((Atraccion) a1);
		atracciones.add((Atraccion) a2);
		Producto p = new PromocionAxB (atracciones, "promo", "", "");
		PromocionDao.insert((Promocion) p);

		assertEquals(4, PromocionDao.countAll());
		
		PromocionDao.delete((Promocion) p);
		
		assertEquals(3, PromocionDao.countAll());
		
		
		
	}
	
	@Test
	public void findPromocion() throws Exception {
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		Producto a1 = new Atraccion("playa", 15, 16, 15, "", "AVENTURA", "");
		Producto a2 = new Atraccion("monta�a", 15, 15, 15, "", "AVENTURA", "");
		atracciones.add((Atraccion) a1);
		atracciones.add((Atraccion) a2);
		Producto p = new PromocionPorcentual (atracciones, "PackAventura", "", 20, "");
		
		assertEquals(p.getNombre(), PromocionDao.findByPromocion("PackAventura").getNombre());
	}
	
}
