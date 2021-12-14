package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.OrdenadorPorCosto;
import model.OrdenadorPorDuracion;
import model.OrdenadorPorPromo;
import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;

public class FilterProductService {

	AttractionService aService = new AttractionService();
	PromocionService pService = new PromocionService();


	public List<Producto> filtrarOrdenarProductos(Usuario u) {
		String preferencia = u.getTipo();
		List<Producto> productosPrefes = new ArrayList();
		List<Producto> atraccionesPrefes = aService.findByTipo(preferencia);
		List<Producto> promosPrefes = pService.findByTipo(preferencia);
		productosPrefes.addAll(atraccionesPrefes);
		productosPrefes.addAll(promosPrefes);
		Collections.sort(productosPrefes, new OrdenadorPorPromo().thenComparing(new OrdenadorPorCosto())
				.thenComparing(new OrdenadorPorDuracion()));

		List<Producto> productosNoPrefes = new ArrayList();
		List<Producto> atraccionesNoPrefes = aService.findByNoTipo(preferencia);
		List<Producto> promosNoPrefes = pService.findByNoTipo(preferencia);
		productosNoPrefes.addAll(atraccionesNoPrefes);
		productosNoPrefes.addAll(promosNoPrefes);
		Collections.sort(productosNoPrefes, new OrdenadorPorPromo().thenComparing(new OrdenadorPorCosto())
				.thenComparing(new OrdenadorPorDuracion()));

		productosPrefes.addAll(productosNoPrefes);
		

		return productosPrefes;

	}
}
