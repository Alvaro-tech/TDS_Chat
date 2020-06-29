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
		//FIJOS
		String nombre = servPersistencia.recuperarPropiedadEntidad(eChat,"nombre");
		String movil = servPersistencia.recuperarPropiedadEntidad(eChat, "movil");
		
		ChatIndividual chatIndividual = new ChatIndividual(nombre, movil);
		chatIndividual.setId(eChat.getId());
		PoolDAO.getUnicaInstancia().addObjeto(chatIndividual.getId(), chatIndividual);
		
		//BIDIRECCIONALES
		String contacto = servPersistencia.recuperarPropiedadEntidad(eChat, "contacto");
		String historial = servPersistencia.recuperarPropiedadEntidad(eChat, "historial");
		String ultimoMensaje = servPersistencia.recuperarPropiedadEntidad(eChat, "ultimoMensaje");
		
		chatIndividual.setContacto(obtenerContactoById(contacto));
		chatIndividual.setHistorial(obtenerHistorialDesdeId(historial));
		chatIndividual.setUltimoMensaje(oobtenerUltimoMensajeDesdeId(ultimoMensaje));
		return chatIndividual;
		
	}
	


//#############################################################
	
	//funcion duplicada en adaptadorDaoChatGrupo
	private LinkedList<Mensaje> obtenerHistorialDesdeId(String hist) {
		LinkedList<Mensaje> historial = new LinkedList<Mensaje>();

		StringTokenizer tok = new StringTokenizer(hist, " ");
		while (tok.hasMoreTokens()) {
			String id = (String) tok.nextElement();
			Mensaje aux = AdaptadorMensajeDAO.getUnicaInstancia().get(Integer.valueOf(id));
			historial.add(aux);
		}
		return historial;
	}

	private Usuario obtenerContactoById(String contacto) {
		return AdaptadorUsuarioDAO.getUnicaInstancia().get(Integer.valueOf(contacto));
	}
	
	private Mensaje oobtenerUltimoMensajeDesdeId(String id) {
		return AdaptadorMensajeDAO.getUnicaInstancia().get(Integer.valueOf(id));
	}
	

	//#############################################################

	private Entidad chatToEntidadInd (ChatIndividual chat) {
		Entidad eChat = new Entidad();
		eChat.setNombre("ChatIndividual");
		
		eChat.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", chat.getNombre()),
						new Propiedad ("movil", chat.getMovil()),
						new Propiedad ("contacto", chat.getUserId().toString()),
						new Propiedad("ultimoMensaje", obtenerIdUltimoMensaje(chat.getUltimoMensaje())),
						new Propiedad("historial", obtenerIdMensajes(chat.getHistorial()) )
								
						))
						
				);
		return eChat;
	}
	
	
	

	@Override
	public void create(ChatIndividual chat) {
		Entidad  eChatInidividual;
		
		// *-*-*-*-*-*-*-*-Control de si existe el objeto ya, para evitar volver a
		// crearlo y haya repetidos
		boolean existe = true;
		// Si la entidad está registrada no la registra de nuevo
		try {
			eChatInidividual = servPersistencia.recuperarEntidad(chat.getId());
		} catch (Exception e) {
			existe = false;
		}
		if (existe)
			return;
		
		eChatInidividual = this.chatToEntidadInd(chat);
		servPersistencia.registrarEntidad(eChatInidividual);
		chat.setId(eChatInidividual.getId());
		
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
	public ChatIndividual get(int id) {
		if (PoolDAO.getUnicaInstancia().contiene(id)) {
			return (ChatIndividual) PoolDAO.getUnicaInstancia().getObjeto(id);
		}
		
		Entidad eChat = servPersistencia.recuperarEntidad(id);
		ChatIndividual chatIndividual = entidadToChatInd(eChat);
		PoolDAO.getUnicaInstancia().addObjeto(id, chatIndividual);
		return chatIndividual;
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
	
	private String obtenerIdUltimoMensaje(Mensaje ultimoMensaje) {
		Integer id =(Integer) ultimoMensaje.getId();
		return id.toString();
	}
	
	
	/*
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
	*/
	
}
