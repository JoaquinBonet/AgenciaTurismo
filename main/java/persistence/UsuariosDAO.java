package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;
import persistence.commons.GenericDAO;

public interface UsuariosDAO extends GenericDAO<Usuario>{

	public abstract int findIdByNombreUsuario(String nombreUsuario);
	public abstract Usuario findByUsername(String nombre);
	public abstract Usuario toUser(ResultSet resultados) throws Exception;

}
