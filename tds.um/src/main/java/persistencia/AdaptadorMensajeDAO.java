package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */

//TODO: Hacer todas las propiedades necesarias para Usuario.
public final class AdaptadorMensajeDAO implements IAdaptadorMensajeDAO {
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorMensajeDAO unicaInstancia = null;
	
	public static AdaptadorMensajeDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorMensajeDAO();
		else
			return unicaInstancia;
	}
	
	public AdaptadorMensajeDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	private Mensaje entidadToMensaje(Entidad eMensaje) {
		
		String emisor = servPersistencia.recuperarPropiedadEntidad(eMensaje, "emisor");
		String receptor = servPersistencia.recuperarPropiedadEntidad(eMensaje, "receptor");
		String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, "texto");
		String fecha = servPersistencia.recuperarPropiedadEntidad(eMensaje, "fecha"); //TODO: Creo que no se carga bien
		
		
		//Mensaje mensaje = new Mensaje(usuario, texto, fecha);
		//mensaje.setId(eMensaje.getId());
		//return mensaje;
		return null;
	}
	
	
	private Entidad MensajeToEntidad(Mensaje mensaje) {
		Entidad  eMensaje = new Entidad();
		eMensaje.setNombre("Mensaje"); 
	
		eMensaje.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("emisor", obtenerIdEmisor(mensaje.getEmisor())),
						new Propiedad("receptor", obtenerIdReceptor(mensaje.getReceptor())),
						new Propiedad("fecha", mensaje.getFecha().toString()),
						new Propiedad("texto", mensaje.getTexto())
						))
				);
		return eMensaje;
	}

	private String obtenerIdReceptor(ChatIndividual receptor) {
		return receptor.getId().toString();
	}

	private String obtenerIdEmisor(Usuario emisor) {
		return null;
	}

	public void create(Mensaje mensaje) {
		Entidad  eMensaje = this.MensajeToEntidad(mensaje);
		eMensaje = servPersistencia.registrarEntidad(eMensaje);
		mensaje.setId(eMensaje.getId());
		
	}

	public boolean delete(Mensaje mensaje) {
		Entidad eMensaje;
		eMensaje = servPersistencia.recuperarEntidad(mensaje.getId());
		return servPersistencia.borrarEntidad(eMensaje);
	}

	public Mensaje getById(Integer id) {
		Entidad eMensaje = servPersistencia.recuperarEntidad(id);
		return entidadToMensaje(eMensaje);
	}

	@Override
	public List<Mensaje> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	

	
}
