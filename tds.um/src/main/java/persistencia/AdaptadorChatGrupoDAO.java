package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
	 * @param mensaje
	 * Crea un objeto entidad (persistencia) a partir de un objeto de la clase ChatGrupo.
	 * Esto nos servirá para la funcion crear
	 * @return un objeto entidad
	 */
	private Entidad ChatGrupoToEntidad(ChatGrupo grupo) {
		Entidad e = new Entidad();
		e.setNombre("ChatGrupo");
		
		e.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList( 
						new Propiedad("nombre", grupo.getNombre()), 
						new Propiedad("historial", obtenerIdMensajes(grupo.getHistorial())),
						new Propiedad("miembros", obtenerMiembros(grupo.getMiembros())),
						new Propiedad("administradores", obtenerAdministradores(grupo.getAdministradores()))
						))
				);
		return e;
		
	}
	
	
	
	@Override
	/**
	 * Funcion crear, crea un objeto por primera vez y lo introduce en el
	 * servicio de persistencia
	 */
	public void create(ChatGrupo grupo) {
		Entidad eChatGrupo = this.ChatGrupoToEntidad(grupo);
		eChatGrupo = servPersistencia.registrarEntidad(eChatGrupo);
		grupo.setId(eChatGrupo.getId());
		
	}
	
	
	private ChatGrupo entidadToChatGrupo(Entidad eGrupo) {
		//String nombreg = servPersistencia.recuperarPropiedadEntidad(eGrupo, "nombre");
		//TODO: HACER!!!! necesitamos Pool
		return null;
	}

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
	 * Funcion get para obtener un ChatGrupo del servicio de persistencia 
	 * a través del Id de este.
	 */
	public ChatGrupo get(int id) {
		Entidad eChatGrupo = servPersistencia.recuperarEntidad(id);
		return entidadToChatGrupo(eChatGrupo);
	}

	@Override
	/**
	 * Funcion para obtener una lista de todos los grupos del catálogo
	 */
	public List<ChatGrupo> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("ChatGrupo");
		
		List<ChatGrupo> grupos  = new LinkedList<ChatGrupo>();
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
	
	//#########FUNCIONES AUXILIARES DE CHATGRUPOTOENTIDAD():############
		/**
		 * Funcion para, del historial de mensajes del grupo
		 * obtener sus ids como strings.
		 * Este historial está compuesto de mensajes.
		 **/
		private String obtenerIdMensajes(LinkedList<Mensaje> mens) {
			String aux = "";
			for(Mensaje iterador : mens) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		
		/**
		 * Funcion para, de los miembros del grupo
		 * obtener sus ids como strings.
		 * Los miembros son chatsIndividuales (Contactos individuales)
		 **/
		private String obtenerMiembros(LinkedList<ChatIndividual> miembros) {
			String aux = "";
			for(ChatIndividual iterador : miembros) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		
		/**
		 * Funcion para, de los administradores del grupo
		 * obtener sus ids como strings.
		 * Estos son usuarios.
		 **/
		private String obtenerAdministradores(HashSet<Usuario> admins) {
			String aux = "";
			for(Usuario iterador : admins) {
				aux +=  iterador.getId() + " ";
			}
			return aux.trim(); 
		}
		

}
