package services;

import model.Usuario;
import model.NullUser;
import persistence.UsuariosDAO;
import persistence.commons.DAOFactory;
import persistence.impl.UsuariosDAOImpl;

public class LoginService {

	public Usuario login(String username) {
		UsuariosDAO userDao = DAOFactory.getUsuariosDao();
    	Usuario user = userDao.findByUsername(username);
    	
    	if (user == null) {
    		user = NullUser.build();
    	}
    	
    	return user;
	}
	
}
