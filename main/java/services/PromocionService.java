package services;

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
import persistence.commons.DAOFactory;

public class PromocionService {

	PromocionesDAO pDAO = DAOFactory.getPromocionesDao();

	public List<Promocion> list() {
		return DAOFactory.getPromocionesDao().findAll();
	}

	public Producto find(String nombre) {

		return pDAO.findByPromocion(nombre);
	}

	public List<Producto> findByTipo(String tipo) {

		return pDAO.findByTipo(tipo);
	}

	public List<Producto> findByNoTipo(String preferencia) {

		return pDAO.findByNoTipo(preferencia);
	}

	public void delete(String name) {
		Promocion p = pDAO.findByPromocion(name);
		pDAO.deleteAtracPromo(name);
		pDAO.delete(p);
	}

	public void create(Promocion p) {

		pDAO.insert(p);
	}

	public Promocion update(ArrayList<Atraccion> atracciones, String name, String info, String img,
			Integer costo, Integer porcentaje) {

		Promocion p = pDAO.findByPromocion(name);
		p.setInfo(info);
		p.setAtracciones(atracciones);
		p.setImg(img);
		
		if(p.getClass().getSimpleName().equals("PromocionAbsoluta")) {
			((PromocionAbsoluta) p).setCostoFijo(costo);
			
			
		} else if (p.getClass().getSimpleName().equals("PromocionPorcentual")) {

			((PromocionPorcentual) p).setPorcentaje(porcentaje);
			
		} 
		
		if (p.isValid()) {
			
			pDAO.update(p);

		}

		return p;
	}



}
