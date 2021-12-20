package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Usuario;
import persistence.commons.DAOFactory;
import persistence.impl.UsuariosDAOImpl;


public class TestDeUsuariosDAO {


	UsuariosDAOImpl uDao = DAOFactory.getUsuariosDao();
	
	@Test
	public void findAllTest() {
		
		assertEquals(5, uDao.findAll().size());

	}

	@Test
	public void findByTipoUsuarioTest() throws Exception {
		Usuario u = new Usuario("Sam", "DEGUSTACION", 15, 15, false);
		assertEquals(u.getNombre(), uDao.findByUsername("Sam").getNombre());
		assertEquals(u.getTipo(), uDao.findByUsername("Sam").getTipo());

	}

	@Test
	public void findIdDTest() {
		assertEquals(2, uDao.findIdByNombreUsuario("Gandalf"));
	}

	@Test
	public void insertDeleteTest() throws Exception {
		
		Usuario u = new Usuario("Carlos", "DEGUSTACION", 15, 15, false);
		uDao.insert(u);
		
		assertEquals(5, uDao.countAll());

		uDao.delete(u);
		assertEquals(4, uDao.countAll());
		

	}

	@Test
	public void findByAtraccionTest() throws Exception {

		Usuario u = new Usuario("Sam", "DEGUSTACION", 15, 15, false);

		assertEquals(u.getNombre(), uDao.findByUsername("Sam").getNombre());
	}

}
