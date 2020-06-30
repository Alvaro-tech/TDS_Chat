package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.ChatGrupo;
import modelo.ChatIndividual;
import modelo.Mensaje;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public final class AdaptadorChatGrupoDAO implements IAdaptadorChatGrupoDAO {

	private ServicioPersistencia servPersistencia;
	private static AdaptadorChatGrupoDAO unicaInstancia = null;

	public static IAdaptadorChatGrupoDAO getUnicaInstancia() { 
		if (unicaInstancia == null)
			return new AdaptadorChatGrupoDAO();
		else
			return unicaInstancia;
	}

	public AdaptadorChatGrupoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/**
	 * 
	 * @param mensaje Crea un objeto entidad (persistencia) a partir de un objeto de
	 *                la clase ChatGrupo. Esto nos servirá para la funcion crear
	 * @return un objeto entidad
	 */
	private Entidad ChatGrupoToEntidad(ChatGrupo grupo) {
		 Entidad e = new Entidad();
	        e.setNombre("ChatGrupo"); 

	        e.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", grupo.getNombre()),
	                new Propiedad("ultimoMensaje", "0"),
	                new Propiedad("historial","0"),
	                new Propiedad("miembros", obtenerMiembros(grupo.getMiembros())),
	                new Propiedad("administradores", obtenerAdministradores(grupo.getAdministradores())))));
	        return e;

	}



	@Override
	/**
	 * Funcion crear, crea un objeto por primera vez y lo introduce en el servicio
	 * de persistencia
	 */
	public void create(ChatGrupo grupo) {
		Entidad eChatGrupo ;

		// *-*-*-*-*-*-*-*-Control de si existe el objeto ya, para evitar volver a
		// crearlo y haya repetidos
		boolean existe = true;
		// Si la entidad está registrada no la registra de nuevo
		try {
			eChatGrupo = servPersistencia.recuperarEntidad(grupo.getId());
		} catch (Exception e) {
			existe = false;
		}
		if (existe)
			return;

		eChatGrupo = this.ChatGrupoToEntidad(grupo);
		eChatGrupo = servPersistencia.registrarEntidad(eChatGrupo);
		grupo.setId(eChatGrupo.getId());

	}

	private ChatGrupo entidadToChatGrupo(Entidad eGrupo) {

		// *-*-*-*-*-*-*-*-*-*--*-*-Objetos Fijos
		String nombreg = servPersistencia.recuperarPropiedadEntidad(eGrupo, "nombre");

		// Crear un ChatGrupo solo con los objetos fijos.
		ChatGrupo grupo = new ChatGrupo(nombreg);
		grupo.setId(eGrupo.getId());
		// Lo guardamos en el Pool (por eficiencia, no por evitar ciclos)
		PoolDAO.getUnicaInstancia().addObjeto(grupo.getId(), grupo);

		// *-*-*-*-*-*-*-*-*-*--*-*-Tratamos las propiedades bi-direccionales
		String ultimoMensaje = servPersistencia.recuperarPropiedadEntidad(eGrupo, "ultimoMensaje");
		String historial = servPersistencia.recuperarPropiedadEntidad(eGrupo, "historial ");
		String miembros = servPersistencia.recuperarPropiedadEntidad(eGrupo, "miembros");
		String administradores = servPersistencia.recuperarPropiedadEntidad(eGrupo, "administradores");

		
		grupo.setMiembros(obtenerMiembrosDesdeId(miembros));
		grupo.setAdministradores(obtenerAdministradoresDesdeId(administradores));
		
		try { //Evitar null pointerExceptions
			grupo.setUltimoMensaje(obtenerUltimoMensaje(ultimoMensaje));
			grupo.setHistorial(obtenerHistorialDesdeId(historial));
		} catch (Exception e) {
			
		}
		return grupo;
	}

//#####################################################################

	private Mensaje obtenerUltimoMensaje(String id) {
		return AdaptadorMensajeDAO.getUnicaInstancia().get(Integer.valueOf(id));
	}

	private HashSet<Usuario> obtenerAdministradoresDesdeId(String admins) {
		HashSet<Usuario> administradores = new HashSet<Usuario>();

		// Parseamos el string admins
		// dividimos el string en array de palabras separadas por " " (id´s)
		StringTokenizer tok = new StringTokenizer(admins, " ");
		// Recorremos el StringTokenizer y recuperamos por id elemento a elemento.
		while (tok.hasMoreTokens()) {
			String id = (String) tok.nextElement();
			Usuario adminAux = AdaptadorUsuarioDAO.getUnicaInstancia().get(Integer.valueOf(id));
			administradores.add(adminAux);
		}
		return administradores;
	}

	private LinkedList<ChatIndividual> obtenerMiembrosDesdeId(String miems) {
		LinkedList<ChatIndividual> miembros = new LinkedList<ChatIndividual>();

		StringTokenizer tok = new StringTokenizer(miems, " ");
		while (tok.hasMoreTokens()) {
			String id = (String) tok.nextElement();
			ChatIndividual aux = AdaptadorChatIndividualDAO.getUnicaInstancia().get(Integer.valueOf(id));
			miembros.add(aux);
		}

		return miembros;
	}

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
	


	public void updateHistorial(ChatGrupo chat) {  //Cada vez que actualizas el historial es porque habra un nuevo ultimo mensaje
		Entidad eChat = servPersistencia.recuperarEntidad(chat.getId());
		servPersistencia.eliminarPropiedadEntidad(eChat, "historial");
		servPersistencia.anadirPropiedadEntidad(eChat, "historial", obtenerIdMensajes(chat.getHistorial()));
		
		servPersistencia.eliminarPropiedadEntidad(eChat, "ultimoMensaje");
		servPersistencia.anadirPropiedadEntidad(eChat, "ultimoMensaje", obtenerUltimoMensaje(chat.getUltimoMensaje()));
	}
	
//#####################################################################

	@Override
	/**
	 * Funcion eliminar, para eliminar una Entidad de la persistencia
	 */
	public boolean delete(ChatGrupo grupo) {
		Entidad eChatGrupo;
		eChatGrupo = servPersistencia.recuperarEntidad(grupo.getId());
		return servPersistencia.borrarEntidad(eChatGrupo);
	}

	@Override
	/**
	 * Funcion get para obtener un ChatGrupo del servicio de persistencia a través
	 * del Id de este.
	 */
	public ChatGrupo get(int id) {
		if (PoolDAO.getUnicaInstancia().contiene(id)) {
			return (ChatGrupo) PoolDAO.getUnicaInstancia().getObjeto(id);
		}
		
		Entidad eChatGrupo = servPersistencia.recuperarEntidad(id);
		ChatGrupo g = entidadToChatGrupo(eChatGrupo);
		PoolDAO.getUnicaInstancia().addObjeto(id, g);
		return g;
	}

	@Override
	/**
	 * Funcion para obtener una lista de todos los grupos del catálogo
	 */
	public List<ChatGrupo> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("ChatGrupo");

		List<ChatGrupo> grupos = new LinkedList<ChatGrupo>();
		for (Entidad e : entidades) {
			grupos.add(get(e.getId()));
		}
		return grupos;
	}

	@Override
	/**
	 * Funcion que permite que se pueda actualizar el nombre de un grupo.
	 */
	public void updateNombre(ChatGrupo grupo, String nuevoNombre) {
		Entidad eGrupo = servPersistencia.recuperarEntidad(grupo.getId());
		servPersistencia.eliminarPropiedadEntidad(eGrupo, "nombre");
		servPersistencia.anadirPropiedadEntidad(eGrupo, "nombre", nuevoNombre);

	}

	// #########FUNCIONES AUXILIARES DE CHATGRUPOTOENTIDAD():############
	/**
	 * Funcion para, del historial de mensajes del grupo obtener sus ids como
	 * strings. Este historial está compuesto de mensajes.
	 **/
	private String obtenerIdMensajes(LinkedList<Mensaje> mens) {
		String aux = "";
		for (Mensaje iterador : mens) {
			aux += iterador.getId() + " ";
		}
		return aux.trim();
	}

	private String obtenerUltimoMensaje(Mensaje ultimoMensaje) {
		Integer id =(Integer) ultimoMensaje.getId();
		return id.toString();
	}
	
	/**
	 * Funcion para, de los miembros del grupo obtener sus ids como strings. Los
	 * miembros son chatsIndividuales (Contactos individuales)
	 **/
	private String obtenerMiembros(LinkedList<ChatIndividual> miembros) {
		String aux = "";
		for (ChatIndividual iterador : miembros) {
			aux += iterador.getId() + " ";
		}
		return aux.trim();
	}

	/**
	 * Funcion para, de los administradores del grupo obtener sus ids como strings.
	 * Estos son usuarios.
	 **/
	private String obtenerAdministradores(HashSet<Usuario> admins) {
		String aux = "";
		for (Usuario iterador : admins) {
			aux += iterador.getId() + " ";
		}
		return aux.trim();
	}

}
