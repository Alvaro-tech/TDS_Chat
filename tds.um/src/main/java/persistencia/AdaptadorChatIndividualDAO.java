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
	
	private ChatIndividual entidadToChatInd(Entidad eChatIndividual) {
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eChatIndividual, "usuario");
		String movil = servPersistencia.recuperarPropiedadEntidad(eChatIndividual, "texto");
		String historial = servPersistencia.recuperarPropiedadEntidad(eChatIndividual, "historial");

		
		
		ChatIndividual chataux = new ChatIndividual(movil ,nombre);
		chataux.setId(eChatIndividual.getId());
		
		for (Mensaje iterador : ObtenerListadoMensajesDesdeId(historial)) {
			chataux.almacenarMensaje(iterador);
		}
		return chataux;
	}
	
	private Entidad ChatIndToEntidad(ChatIndividual chat) {
		Entidad  eMensaje = new Entidad();
		eMensaje.setNombre("ChatIndividual"); 
	
		eMensaje.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("movil", chat.getmovil()), 
						new Propiedad("nombre", chat.getNombre()),
						new Propiedad("historial", obtenerIdListadoMensajes(chat.getHistorial()))
						))
				);
		return eMensaje;
	}

	public void create(ChatIndividual Usuario) {
		Entidad eUsuario;
		eUsuario = this.ChatIndToEntidad(Usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		Usuario.setId(eUsuario.getId());
		
	}

	public boolean delete(ChatIndividual Usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(Usuario.getId());
		return servPersistencia.borrarEntidad(eUsuario);
	}


	public ChatIndividual get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		return entidadToChatInd(eUsuario);
	}

	public List<ChatIndividual> getAll(int idPadre) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateHistorial(ChatIndividual Usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(Usuario.getId());

		servPersistencia.eliminarPropiedadEntidad(eUsuario, "historial");
		System.out.println("Despues del update:");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "historial", obtenerIdListadoMensajes(Usuario.getHistorial()));
		
		
	}
	//// FUncionalidad auxiliar
	
	private String obtenerIdListadoMensajes(LinkedList<Mensaje> listaMensajes) {
		String aux = "";
		for(Mensaje iterador : listaMensajes) {
			aux += iterador.getId() + " ";
		}
		
		return aux.trim();
	}
	
	private List<Mensaje> ObtenerListadoMensajesDesdeId (String chatsG){
		List<Mensaje> listado = new LinkedList<Mensaje>();
		
		StringTokenizer strTok = new StringTokenizer(chatsG, " ");
		while (strTok.hasMoreTokens()) {
			String id = (String) strTok.nextElement(); 
        	Mensaje mensajeAux = AdaptadorMensajeDAO.getUnicaInstancia().get(Integer.valueOf(id));
			listado.add(mensajeAux);
			}
		
		return listado;
	}

	

	
}
