package services;

import java.util.List;

import model.Usuario;
import persistence.commons.DAOFactory;
import persistence.impl.UsuariosDAOImpl;

public class UserService {

	UsuariosDAOImpl uDao = DAOFactory.getUsuariosDao();
	
	public List<Usuario> list() {
		return uDao.findAll();
	}

	public Usuario create(String nombre, String tipo, double tiempo, int presupuesto, Boolean admin) {
		Usuario user = new Usuario(nombre, tipo, tiempo, presupuesto, false);

		if (user.isValid()) {
			uDao.insert(user);
		
		}

		return user;
	}

	public Usuario find(String nombre) {
		Usuario user = uDao.findByUsername(nombre);
		return user;
	}
	
	public Usuario update(String nombre, String tipo, double tiempo, int presupuesto) {
		
		Usuario user = uDao.findByUsername(nombre);
		user.setTipo(tipo);
		user.setTiempo(tiempo);
		user.setPresupuesto(presupuesto);
		
		if (user.isValid()) {
			uDao.update(user);

		}
		
		return user;
	}

	public void delete(String name) {
		Usuario user = uDao.findByUsername(name);
		uDao.delete(user);
		
	}
}
