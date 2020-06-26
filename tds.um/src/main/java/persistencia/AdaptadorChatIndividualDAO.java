package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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

	
	//-------Funciones para el tratamiento de entidades--------
	
	
	private ChatIndividual entidadToChatInd(Entidad eChat) {
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eChat,"nombre");
		String movil = servPersistencia.recuperarPropiedadEntidad(eChat, "movil");
		String contacto = servPersistencia.recuperarPropiedadEntidad(eChat, "contacto");
		String historial = servPersistencia.recuperarPropiedadEntidad(eChat, "historial");
		
		LinkedList<Mensaje> histoAux = getAllMensajesById(historial);
		Usuario contAux = AdaptadorUsuarioDAO.getUnicaInstancia().get(Integer.valueOf(contacto));
		
		//ChatIndividual(String movil, String nombre, LinkedList<Mensaje> historial, Usuario contacto) 
		ChatIndividual chatInd = new ChatIndividual(movil, nombre, histoAux, contAux);
		chatInd.setId(eChat.getId());
		return chatInd;
		
	}
	

	private Entidad chatToEntidadInd (ChatIndividual chat) {
		Entidad eChat = new Entidad();
		eChat.setNombre("ChatIndividual");
		
		eChat.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", chat.getNombre()),
						new Propiedad ("movil", chat.getMovil()),
						new Propiedad ("contacto", chat.getUserId().toString()),
						new Propiedad("historial", obtenerIdMensajes(chat.getHistorial()) )
								
						))
						
				);
		return eChat;
	}
	
	
	

	@Override
	public void create(ChatIndividual chat) {
		Entidad eChat;
		
		//TODO: POOL
		
		eChat = this.chatToEntidadInd(chat);
		eChat = this.servPersistencia.registrarEntidad(eChat);
		chat.setId(eChat.getId());
		
	}

	@Override
	public boolean delete(ChatIndividual chat) {
		Entidad eChat;
		eChat = servPersistencia.recuperarEntidad(chat.getId());
		return servPersistencia.borrarEntidad(eChat);
	}

	@Override
	public void updateHistorial(ChatIndividual chat) {
		Entidad eChat = servPersistencia.recuperarEntidad(chat.getId());
		
		servPersistencia.eliminarPropiedadEntidad(eChat, "historial");
		servPersistencia.anadirPropiedadEntidad(eChat, "historial", obtenerIdMensajes(chat.getHistorial()));
		
	}

	@Override
	public ChatIndividual get(int idChat) {
		Entidad eChat = servPersistencia.recuperarEntidad(idChat);
		return entidadToChatInd(eChat);
	}

	//TODO: Necesaria esta función???????
	@Override
	public List<ChatIndividual> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	//---------------Funcionalidad complemetaria-----------
	
	
	private String obtenerIdMensajes(LinkedList<Mensaje> historial) {
		String aux = "";
		for(Mensaje men : historial) {
			aux += men.getId() + " ";
		}
		return aux.trim(); 
	}
	
	private LinkedList<Mensaje> getAllMensajesById(String historial) {
		LinkedList<Mensaje> mensajes = new LinkedList<Mensaje>();
		StringTokenizer strTok = new StringTokenizer(historial, " ");
		while (strTok.hasMoreTokens()) {
			String id = (String) strTok.nextElement(); 
			Mensaje menAux = AdaptadorMensajeDAO.getUnicaInstancia().get(Integer.valueOf(id));
			mensajes.add(menAux);
		}
		return mensajes;
	}
	
}
