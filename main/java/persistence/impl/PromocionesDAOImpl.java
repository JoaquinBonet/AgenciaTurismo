package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import persistence.AtraccionesDAO;
import persistence.PromocionesDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class PromocionesDAOImpl implements PromocionesDAO {

	private ArrayList<Promocion> promociones = new ArrayList<Promocion>();

	public ArrayList<Promocion> findAll() {

		try {
			String sql = "SELECT * FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {

				promociones.add(toPromocion(resultados));

			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Promocion findByPromocion(String nombrePromo) {
		try {
			String sql = "SELECT * FROM PROMOCIONES WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombrePromo);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		int total = 0;

		try {
			String sql = "SELECT count(*) AS TOTAL FROM PROMOCIONES";
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

	public ArrayList<Atraccion> findAtraccionesDePromocion(String tipoPromo, String nombrePromo) {
		ArrayList<Atraccion> a = null;
		try {
		String sql = 	"select A.nombre, A.cupo, A.costo, A.tiempo,  A.info, TA.tipo_atraccion, A.img_url from PROMOCIONES as P \r\n"
					+ "						JOIN TIPODEATRACCION AS TA ON TA.id_tipo_atraccion = a.id_tipo_atraccion \r\n"
					+ "						JOIN ATRACCIONES_DE_PROMOS AS PA ON PA.id_promo = P.id_promo \r\n"
					+ "						JOIN ATRACCIONES AS A ON PA.id_atraccion = a.id_atraccion \r\n"
					+ "						WHERE P.tipo_promo LIKE  ? and P.nombre like ?";
			a = new ArrayList<Atraccion>();
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipoPromo);
			statement.setString(2, nombrePromo);
	
			ResultSet results = statement.executeQuery();

			AtraccionesDAOImpl AtraccionDao = DAOFactory.getAtraccionesDao();

			while (results.next()) {
				
				a.add(AtraccionDao.toAtraccion(results));

			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

		return a;
	}

	public Promocion toPromocion(ResultSet resultados) {

		Promocion rta = null;
		Promocion rta1 = null;
		Promocion rta2 = null;
		try {

			if (resultados.getString(3).equals("PromocionAxB")) {
				
				ArrayList<Atraccion> a = findAtraccionesDePromocion("PromocionAxB", resultados.getString(4));

				rta = new PromocionAxB(a, resultados.getString(4), resultados.getString(8), resultados.getString(9));

			} else if (resultados.getString(3).equals("PromocionPorcentual")) {

				
				ArrayList<Atraccion> a = findAtraccionesDePromocion("PromocionPorcentual", resultados.getString(4));

				rta1 = new PromocionPorcentual(a, resultados.getString(4), resultados.getString(8),
						resultados.getInt(6), resultados.getString(9));

			} else if (resultados.getString(3).equals("PromocionAbsoluta")) {
				
	
				ArrayList<Atraccion> a = findAtraccionesDePromocion("PromocionAbsoluta", resultados.getString(4));

				rta2 = new PromocionAbsoluta(a, resultados.getString(4), resultados.getString(8), resultados.getInt(7),
						resultados.getString(9));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rta != null ? rta : rta1 != null ? rta1 : rta2;

	}

	@Override
	public void agregarVisitante(Promocion p) {
		try {
			AtraccionesDAOImpl AtraccionDao = DAOFactory.getAtraccionesDao();
			ArrayList<Atraccion> arrayAtracciones = p.getAtracciones();
			for (Atraccion a : arrayAtracciones) {
				a.agregarVisitantes(1);
				AtraccionDao.update(a);
			}

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		

	}

	public int delete(Promocion p) {
		try {
			String sql = "DELETE FROM PROMOCIONES WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, p.getNombre());
			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insert(Promocion p) {
		try {
			AtraccionesDAO aDao = DAOFactory.getAtraccionesDao();
			PromocionesDAO pDao = DAOFactory.getPromocionesDao();

			if (p.getClass().getSimpleName().equals("PromocionPorcentual")) {
				insertPromoPorcentual(p);
			} else if (p.getClass().getSimpleName().equals("PromocionAbsoluta")) {
				insertPromoAbsoluta(p);
			} else {
				insertPromoAxB(p);

			}

			int id_promo = pDao.findIdByNombrePromocion(p.getNombre());
		
			for (Atraccion ca : p.getAtracciones()) {
				int id_atra = aDao.findIdByNombreAtraccion(ca.getNombre());
			
				insertAtraccionDePromo(id_atra, id_promo);
			}

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return 0;

	}

	private int insertAtraccionDePromo(int id_atraccion, int id_promo) {
		try {
			String sql = "INSERT INTO ATRACCIONES_DE_PROMOS (id_atraccion, id_promo) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_atraccion);
			statement.setInt(2, id_promo);

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int insertPromoPorcentual(Promocion p) {
		try {
			String sql = "INSERT INTO PROMOCIONES (id_tipo_producto, tipo_promo, nombre, id_tipo_atraccion, descuento, precio, info, img_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, p.getIdTipoProducto(p));
			String tipoPromo = p.getClass().getSimpleName();
			statement.setString(2, tipoPromo);
			statement.setString(3, p.getNombre());
			statement.setInt(4, p.getCodigoAtraccion());
			statement.setInt(5, ((PromocionPorcentual) p).getPorcentaje());
			statement.setInt(6, p.getCosto());
			statement.setString(7, p.getInfo());
			statement.setString(8, p.getImg());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insertPromoAbsoluta(Promocion p) {
		try {
			String sql = "INSERT INTO PROMOCIONES (id_tipo_producto, tipo_promo, nombre, id_tipo_atraccion, descuento, precio, info, img_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, p.getIdTipoProducto(p));
			String tipoPromo = p.getClass().getSimpleName();
			statement.setString(2, tipoPromo);
			statement.setString(3, p.getNombre());
			statement.setInt(4, p.getCodigoAtraccion());

			statement.setInt(6, ((PromocionAbsoluta) p).getCosto());
			statement.setString(7, p.getInfo());
			statement.setString(8, p.getImg());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insertPromoAxB(Promocion p) {
		try {
			String sql = "INSERT INTO PROMOCIONES (id_tipo_producto, tipo_promo, nombre, id_tipo_atraccion, descuento, precio, info, img_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, p.getIdTipoProducto(p));
			String tipoPromo = p.getClass().getSimpleName();
			statement.setString(2, tipoPromo);
			statement.setString(3, p.getNombre());
			statement.setInt(4, p.getCodigoAtraccion());
			statement.setString(7, p.getInfo());
			statement.setString(8, p.getImg());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int findIdByNombrePromocion(String nombrePromo) {
		try {
			String sql = "SELECT id_promo FROM PROMOCIONES WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombrePromo);
			ResultSet resultados = statement.executeQuery();

			int id_promo = resultados.getInt(1);

			return id_promo;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Producto> findByTipo(String tipoString) {
		try {
			String sql = "select promociones.id_promo, promociones.id_tipo_producto, promociones.tipo_promo, promociones.nombre, TIPODEATRACCION.id_tipo_atraccion, promociones.descuento, PROMOCIONES.precio, PROMOCIONES.info, PROMOCIONES.img_url from promociones join TIPODEATRACCION where Promociones.id_tipo_atraccion = TIPODEATRACCION.id_tipo_atraccion and TIPODEATRACCION.tipo_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipoString);
			ResultSet resultados = statement.executeQuery();

			List<Producto> promociones = new ArrayList();

			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Producto> findByNoTipo(String tipoString) {
		try {
			String sql = "select promociones.id_promo, promociones.id_tipo_producto, promociones.tipo_promo, promociones.nombre, TIPODEATRACCION.id_tipo_atraccion, promociones.descuento, PROMOCIONES.precio, PROMOCIONES.info, PROMOCIONES.img_url from promociones join TIPODEATRACCION where Promociones.id_tipo_atraccion = TIPODEATRACCION.id_tipo_atraccion and TIPODEATRACCION.tipo_atraccion != ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipoString);
			ResultSet resultados = statement.executeQuery();

			List<Producto> promociones = new ArrayList();

			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int deleteAtracPromo(String nombrePromo) {
		try {
			PromocionesDAO pDao = DAOFactory.getPromocionesDao();
			String sql = "delete from ATRACCIONES_DE_PROMOS where ATRACCIONES_DE_PROMOS.id_promo = ?";
			Connection conn = ConnectionProvider.getConnection();
			
			int id = pDao.findIdByNombrePromocion(nombrePromo);
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		
	}

	@Override
	public int update(Promocion t) {
		try {
			String sql = "UPDATE PROMOCIONES SET descuento = ?, precio = ?, info = ?,  img_url = ? WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			
			PreparedStatement statement = conn.prepareStatement(sql);
			editAtracPromo(t.getNombre(), t.getAtracciones());
			statement.setDouble(1, t.getPorcentaje());
			statement.setInt(2, t.getCosto());
			statement.setString(3, t.getInfo());
			//statement.setInt(5, t.getCodigoAtraccion());
			statement.setString(4, t.getImg());
			statement.setString(5, t.getNombre());
			
			
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int editAtracPromo(String nombrePromo, ArrayList<Atraccion> atracciones) {
		try {
			PromocionesDAOImpl pDao = DAOFactory.getPromocionesDao();
			AtraccionesDAO aDao = DAOFactory.getAtraccionesDao();
		
			pDao.deleteAtracPromo(nombrePromo);
			int id_promo = pDao.findIdByNombrePromocion(nombrePromo);
			int id_atrac = 0;
			for (Atraccion ca : atracciones) {
				id_atrac = aDao.findIdByNombreAtraccion(ca.getNombre());
				pDao.insertAtraccionDePromo(id_atrac, id_promo);
			}
			

			return 0;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		
	}

}
