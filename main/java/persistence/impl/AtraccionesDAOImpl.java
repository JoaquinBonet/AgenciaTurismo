package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Producto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.AtraccionesDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class AtraccionesDAOImpl implements AtraccionesDAO {

	private int tipo_producto = 1;

	public int insert(Atraccion t) {

		try {
			String sql = "INSERT INTO ATRACCIONES (id_tipo_producto, nombre, cupo, costo, tiempo,info, id_tipo_atraccion, img_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, tipo_producto);
			
			statement.setString(2, t.getNombre());
			statement.setInt(3, t.getCupo());
			statement.setInt(4, t.getCosto());
			statement.setDouble(5, t.getDuracion());
			statement.setString(6, t.getInfo());
			statement.setObject(7, t.getCodigoAtraccion());
			statement.setString(8, t.getImg());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Atraccion t) {
		try {
			String sql = "UPDATE ATRACCIONES SET costo = ?, tiempo = ?, cupo = ?, info = ?, id_tipo_atraccion = ?, img_url = ? WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getCosto());
			statement.setDouble(2, t.getDuracion());
			statement.setInt(3, t.getCupo());
			statement.setString(4, t.getInfo());
			statement.setInt(5, t.getCodigoAtraccion());
			statement.setString(6, t.getImg());
			statement.setString(7, t.getNombre());
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int delete(Atraccion t) {

		try {
			String sql = "DELETE FROM ATRACCIONES WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Atraccion> findAll() {
		try {
			String sql = "select nombre, cupo, costo, tiempo, info, TIPODEATRACCION.tipo_atraccion, img_url from ATRACCIONES join TIPODEATRACCION where ATRACCIONES.id_tipo_atraccion = TIPODEATRACCION.id_tipo_atraccion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				atracciones.add((Atraccion) toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atraccion findByAtraccion(String nombreAtraccion) {
		try {
			String sql = "select nombre, cupo, costo, tiempo, info, TIPODEATRACCION.tipo_atraccion, img_url from ATRACCIONES join TIPODEATRACCION where ATRACCIONES.id_tipo_atraccion = TIPODEATRACCION.id_tipo_atraccion AND nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreAtraccion);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = (Atraccion) toAtraccion(resultados);
			}

			return atraccion;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		int total = 0;

		try {
			String sql = "SELECT count(*) AS TOTAL FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			if (resultados.next()) {
				total = resultados.getInt("TOTAL");
			}

			return total;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atraccion toAtraccion(ResultSet resultados) throws Exception {

		return new Atraccion(resultados.getString(1), resultados.getInt(2), resultados.getInt(3),
				resultados.getDouble(4), resultados.getString(5),
				resultados.getString(6),resultados.getString(7));
	}

	public Producto toProductoAtraccion(ResultSet resultados) throws Exception {
		return new Atraccion(resultados.getString(1), resultados.getInt(2), resultados.getInt(3),
				resultados.getDouble(4), resultados.getString(5),
				resultados.getString(6), resultados.getString(7));
	}

	public int findIdByNombreAtraccion(String nombreAtraccion) {
		try {
			String sql = "SELECT id_atraccion FROM ATRACCIONES WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreAtraccion);
			ResultSet resultados = statement.executeQuery();

			int id_atraccion = resultados.getInt(1);

			return id_atraccion;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Producto> findByTipo(String tipo) {
		try {
			String sql = "select nombre, cupo, costo, tiempo, info, TIPODEATRACCION.tipo_atraccion, img_url from ATRACCIONES join TIPODEATRACCION where ATRACCIONES.id_tipo_atraccion = TIPODEATRACCION.id_tipo_atraccion AND TIPODEATRACCION.tipo_atraccion = ? ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, tipo);
			ResultSet resultados = statement.executeQuery();

			List<Producto> atracciones = new ArrayList();
			
			while (resultados.next()) {
				atracciones.add((Atraccion) toAtraccion(resultados));
			
			}
			
			return atracciones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Producto> findByNoTipo(String tipoString) {
		try {
			String sql = "select nombre, cupo, costo, tiempo, info, TIPODEATRACCION.tipo_atraccion, img_url from ATRACCIONES join TIPODEATRACCION where ATRACCIONES.id_tipo_atraccion = TIPODEATRACCION.id_tipo_atraccion AND TIPODEATRACCION.tipo_atraccion != ? ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, tipoString);
			ResultSet resultados = statement.executeQuery();

			List<Producto> atracciones = new ArrayList<Producto>();
			
			while (resultados.next()) {
				atracciones.add((Atraccion) toAtraccion(resultados));
			
			}
			
			return atracciones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
