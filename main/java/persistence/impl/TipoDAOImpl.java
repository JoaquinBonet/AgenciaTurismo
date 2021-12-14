package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Producto;
import persistence.TipoDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class TipoDAOImpl implements TipoDAO {

	@Override
	public List<String> findAll() {
		try {
			String sql = "select * from TIPODEATRACCION";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<String> tipos = new ArrayList<String>();
			while (resultados.next()) {

				tipos.add(resultados.getString(2));
			}

			return tipos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(String t) {
		try {
			String sql = "INSERT INTO TIPODEATRACCION (tipo_atraccion) VALUES (?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t);

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override

	public int delete(String t) {
		try {
			String sql = "DELETE FROM TIPODEATRACCION WHERE tipo_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public String findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM TIPODEATRACCION WHERE tipo_atraccion like ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			String t = null;
			if (resultados.next()) {
				t = resultados.getString(2);
			}

			return t;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int findIdByNombre(String nombre) {
		try {
			String sql = "SELECT id_tipo_atraccion FROM TIPODEATRACCION WHERE tipo_atraccion like ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			int id_atraccion = resultados.getInt(1);

			return id_atraccion;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public boolean exists(String nombre) {
		try {
			String sql = "SELECT * FROM TIPODEATRACCION WHERE tipo_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();
			boolean rta;
			if (!resultados.next()) {
				rta = false;
			} else {
				rta = true;
			}
			return rta;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(String t) {
		try {
			String sql = "UPDATE ATRACCIONES SET tipo_atraccion = ? WHERE tipo_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
	;
			statement.setString(1, t);
		
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
