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
public final class AdaptadorChatIndividualDAO implements IAdaptadorChatIndividualDAO {
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorChatIndividualDAO unicaInstancia = null;
	
	public static AdaptadorChatIndividualDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorChatIndividualDAO();
		else
			return unicaInstancia;
	}
	
	public AdaptadorChatIndividualDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private Mensaje entidadToMensaje(Entidad eChatIndividual) {
		
		String usuario = servPersistencia.recuperarPropiedadEntidad(eChatIndividual, "usuario");
		String texto = servPersistencia.recuperarPropiedadEntidad(eChatIndividual, "texto");
		String fecha = servPersistencia.recuperarPropiedadEntidad(eChatIndividual, "fecha"); //TODO: Creo que no se carga bien
		
		
		Mensaje mensaje = new Mensaje(usuario, texto, fecha);
		mensaje.setId(eMensaje.getId());
		return mensaje;
	}
	
	private Entidad MensajeToEntidad(Mensaje mensaje) {
		Entidad  eMensaje = new Entidad();
		eMensaje.setNombre("Mensaje"); 
	
		eMensaje.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("usuario", mensaje.getUsuario()), 
						new Propiedad("fecha", mensaje.getFecha().toString()),
						new Propiedad("texto", mensaje.getTexto())
						))
				);
		return eMensaje;
	}

	public void create(ChatIndividual Usuario) {
		// TODO Auto-generated method stub
		
	}

	public boolean delete(ChatIndividual Usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public void updatePerfil(ChatIndividual Usuario) {
		// TODO Auto-generated method stub
		
	}

	public ChatIndividual get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ChatIndividual> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
